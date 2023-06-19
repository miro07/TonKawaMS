package auth.services.authservices;

import auth.dao.UserDao;
import auth.entities.User;
import auth.entities.dto.SellerDto;
import auth.payload.request.SignupRequest;
import auth.payload.response.MessageResponse;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CrmAuthService implements AuthService<User> {
    @Autowired
    UserDao userDao;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public SignupResult<User> signup(SignupRequest signupRequest) throws IOException, MessagingException {
        if (userDao.existsByEmail(signupRequest.getEmail())) {
            return new SignupResult<>(null, new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(signupRequest.getEmail()
                ,signupRequest.getUsername()
                ,encoder.encode(signupRequest.getPassword()));
        User regestredUser = userDao.save(user);

        return new SignupResult<>(user, null);
    }
}
