package auth.security.jwt;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import tonkawa.security.services.UserDetailsImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class JwtUtilsTest {


    @InjectMocks
    private JwtUtils jwtUtils;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void GenerateJwtToken() {
        // Arrange
        UserDetailsImpl userPrincipal = new UserDetailsImpl(1L,"username","email", "password");
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        jwtUtils.setJwtSecret("jwtSecret test");
        jwtUtils.setJwtExpirationMs(6000000);
        // Act
        String token = jwtUtils.generateJwtToken(authentication);

        // Assert
        assertNotNull(token);
        // You can add more assertions if needed
    }

    @Test
    public void GetUserNameFromJwtToken() {
        // Act
        String token = generateMockToken();
        String username = jwtUtils.getUserNameFromJwtToken(token);

        // Assert
        assertNotNull(username);
        // You can add more assertions if needed
    }

    @Test
    public void ValidateJwtToken_InvalidToken() {
        // Arrange
        String invalidToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNaXJvOTUiLCJpYXQiOjE2ODYyNTYwNTksImV4cCI6MTY4NjM0MjQ1OX0.dCecSQzPPsXS46sapTVRfuv5T84K_VD_hzCPA1VkyTen-_qEH3CoWvM4LyBYvNJ36CASYvmE3aMLO1DyeL-17g";

        // Act
        boolean isValid = jwtUtils.validateJwtToken(invalidToken);

        // Assert
        assertFalse(isValid);
    }

    @Test
    public void ValidateJwtToken_ValidToken() {
        String validToken = generateMockToken();
        // Act
        boolean isValid = jwtUtils.validateJwtToken(validToken);
        // Assert
        assertTrue(isValid);
    }
    public String generateMockToken(){
        UserDetailsImpl userPrincipal = new UserDetailsImpl(1L,"username","email", "password");
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        jwtUtils.setJwtSecret("jwtSecret test");
        jwtUtils.setJwtExpirationMs(6000000);
        return jwtUtils.generateJwtToken(authentication);
    }
}
