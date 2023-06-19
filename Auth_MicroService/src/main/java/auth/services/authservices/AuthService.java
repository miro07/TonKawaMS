package auth.services.authservices;


import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import auth.payload.request.SignupRequest;

import java.io.IOException;

@Service
public interface AuthService<T> {

    SignupResult<T> signup(SignupRequest signupRequest) throws IOException, MessagingException;
}
