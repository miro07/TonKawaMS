package auth.services.authservices;

import auth.dao.UserDao;
import auth.entities.User;
import auth.entities.dto.SellerDto;
import auth.payload.request.SignupRequest;
import auth.payload.response.MessageResponse;
import auth.services.barcodeservices.BarcodeGeneratorExecuter;
import auth.services.barcodeservices.BarcodeGeneratorStrategy;
import auth.services.converterservices.ObjectToJsonConverterImpl;
import auth.services.mailservices.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class SellerAuthServiceImpl implements AuthService<User> {
    @Autowired
    UserDao sellerDao;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    MailService mailService;
    @Autowired
    ObjectToJsonConverterImpl objectToJsonConverter;
    @Override
    public SignupResult<User> signup(SignupRequest signupRequest) throws IOException, MessagingException {
        if(sellerDao.existsByEmail(signupRequest.getEmail())){
            return new SignupResult<>(null, new MessageResponse("Error: Email is already in use!"));}

        User user = new User(signupRequest.getEmail(),
                                signupRequest.getUsername()
                                 ,encoder.encode(signupRequest.getPassword()));

        BarcodeGeneratorStrategy barcodeGeneratorStrategy =  BarcodeGeneratorExecuter.barcodeGeneratorStrategy("zxing");
        sellerDao.save(user);
        String signupData =  objectToJsonConverter.convert(new SellerDto(signupRequest.getUsername(), signupRequest.getPassword()));
        mailService.sendSignupEmail(user.getEmail()
                                          , "Signup Successful!"
                                          , barcodeGeneratorStrategy.generate(signupData,300, 100)
                                          , user.getUsername()
                                          );

        return new SignupResult<>(user, null);
    }
}
