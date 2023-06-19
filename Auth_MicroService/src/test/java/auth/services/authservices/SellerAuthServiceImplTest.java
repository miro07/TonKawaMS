package auth.services.authservices;

import auth.dao.UserDao;
import auth.entities.User;
import auth.entities.dto.SellerDto;
import auth.payload.request.SignupRequest;
import auth.services.authservices.SellerAuthServiceImpl;
import auth.services.authservices.SignupResult;
import auth.services.converterservices.ObjectToJsonConverterImpl;
import auth.services.mailservices.MailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SellerAuthServiceImplTest {
    @Mock
    private UserDao sellerDao;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private MailService mailService;

    @Mock
    private ObjectToJsonConverterImpl objectToJsonConverter;

    @InjectMocks
    private SellerAuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSignup() throws IOException, MessagingException {
        // Arrange
        SignupRequest signupRequest = new SignupRequest("username", "email", "password");
        User user = new User("email","username", "encodedPassword");


        when(sellerDao.existsByEmail(signupRequest.getEmail())).thenReturn(false);
        when(encoder.encode(signupRequest.getPassword())).thenReturn("encodedPassword");
        when(objectToJsonConverter.convert(any(SellerDto.class))).thenReturn("signupData");


        // Act
        SignupResult<User> result = authService.signup(signupRequest);

        // Assert
        assertNotNull(result);
        assertNull(result.getMessageResponse());
        assertEquals(user, result.getUser());

        verify(sellerDao).existsByEmail(signupRequest.getEmail());
        verify(sellerDao).save(user);
        verify(encoder).encode(signupRequest.getPassword());
        verify(objectToJsonConverter).convert(any(SellerDto.class));
    }

}
