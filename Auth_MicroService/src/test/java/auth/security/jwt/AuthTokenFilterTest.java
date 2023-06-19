package auth.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import tonkawa.security.services.UserDetailsServiceImpl;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthTokenFilterTest {
    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @Mock
    private WebAuthenticationDetailsSource authenticationDetailsSource;

    @InjectMocks
    private AuthTokenFilter authTokenFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testDoFilterInternal_WithValidJwtToken() throws ServletException, IOException {
        // Arrange
        String jwtToken = "validJwtToken";
        String username = "testUser";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwtToken);
        when(jwtUtils.validateJwtToken(jwtToken)).thenReturn(true);
        when(jwtUtils.getUserNameFromJwtToken(jwtToken)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Act
        authTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(request).getHeader("Authorization");
        verify(jwtUtils).validateJwtToken(jwtToken);
        verify(jwtUtils).getUserNameFromJwtToken(jwtToken);
        verify(userDetailsService).loadUserByUsername(username);
        verify(userDetails).getAuthorities();
        verify(filterChain).doFilter(request, response);
    }
    @Test
    public void testDoFilterInternal_WithInvalidJwtToken() throws ServletException, IOException {
        // Arrange
        String jwtToken = "invalidJwtToken";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwtToken);
        when(jwtUtils.validateJwtToken(jwtToken)).thenReturn(false);

        // Act
        authTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(request).getHeader("Authorization");
        verify(jwtUtils).validateJwtToken(jwtToken);
        verify(filterChain).doFilter(request, response);
    }
    @Test
    public void testDoFilterInternal_WithoutJwtToken() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        authTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(request).getHeader("Authorization");
        verify(filterChain).doFilter(request, response);
    }
}
