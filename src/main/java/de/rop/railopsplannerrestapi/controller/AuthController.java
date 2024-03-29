package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.EnumRole;
import de.rop.railopsplannerrestapi.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import de.rop.railopsplannerrestapi.entity.User;
import de.rop.railopsplannerrestapi.repository.UserRepository;
import de.rop.railopsplannerrestapi.request.AuthRequest;
import de.rop.railopsplannerrestapi.security.JwtTokenProvider;

import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @CrossOrigin
    @PostMapping(value = "/sign-up")
    public ResponseEntity<User> register(@RequestBody AuthRequest authRequest) {
        System.out.println(authRequest.getPassword());
        System.out.println(authRequest.getEmail());
        System.out.println(authRequest.getFirstName());
        System.out.println(authRequest.getLastName());
        System.out.println(authRequest.getInitials());

        Optional<User> userOptional = userRepository.findUserByEmail(authRequest.getEmail());

        // Check, if email already in use.
        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setFirstName(authRequest.getFirstName());
        user.setLastName(authRequest.getLastName());
        user.setFax(authRequest.getFax());
        user.setInitials(authRequest.getInitials());
        user.setPhone(authRequest.getPhone());
        user.setRole(EnumRole.ROLE_READER);
        User created = userRepository.save(user);

        return ResponseEntity.ok(created);
    }

    @CrossOrigin
    @PostMapping(value = "/sign-in")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
    }
}
