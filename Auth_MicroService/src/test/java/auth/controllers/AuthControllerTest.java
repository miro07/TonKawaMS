package auth.controllers;

import auth.entities.User;
import auth.payload.request.LoginRequest;
import auth.payload.request.SignupRequest;
import auth.security.jwt.JwtUtils;
import auth.security.services.UserDetailsImpl;
import auth.services.authservices.SellerAuthServiceImpl;
import auth.services.authservices.SignupResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private SellerAuthServiceImpl authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testuser");
        signupRequest.setPassword("testpassword");
        SignupResult<User> signupResult = new SignupResult<>();
        signupResult.setUser(new User());
        when(authService.signup(signupRequest)).thenReturn(signupResult);
        ResponseEntity<?> responseEntity = authController.registerUser(signupRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(signupResult.getUser(), responseEntity.getBody());
    }

    @Test
    public void testAuthenticateUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("testjwt");
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getId()).thenReturn(1L);
        when(userDetails.getUsername()).thenReturn("testuser");
        when(userDetails.getEmail()).thenReturn("testemail@test.com");
        when(jwtUtils.getJwtExpirationMs()).thenReturn(1000);
        ResponseEntity<?> responseEntity = authController.authenticateUser(loginRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}