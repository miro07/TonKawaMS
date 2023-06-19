package auth.controllers;

import auth.entities.User;
import auth.payload.request.LoginRequest;
import auth.payload.request.SignupRequest;
import auth.payload.response.JwtResponse;
import auth.security.jwt.JwtUtils;
import auth.security.services.UserDetailsImpl;
import auth.services.authservices.AuthFactory;
import auth.services.authservices.SignupResult;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthFactory authFactory;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) throws MessagingException, IOException {
        SignupResult<User> signupResult = authFactory.execute(signupRequest.getApplication()).signup(signupRequest);
        if(signupResult.getUser() == null){
            return ResponseEntity
                    .badRequest()
                    .body(signupResult.getMessageResponse());
        }
        return ResponseEntity.ok(signupResult.getUser());
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer",
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                jwtUtils.getJwtExpirationMs()
        ));
    }
}
