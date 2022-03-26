package de.rop.railopsplannerrestapi.controller;

import org.springframework.web.bind.annotation.*;
import de.rop.railopsplannerrestapi.entity.User;
import de.rop.railopsplannerrestapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<User> index() {
        return userRepository.findAll();
    }

    @PostMapping("/email")
    public Optional<User> findUserByEmail(@RequestBody User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        System.out.println(user.getEmail());
        if(userOptional.isEmpty()){
            User userResponse = new User();
            userResponse.setEmail("WRONG_MAIL");
            return Optional.of(userResponse);
        }

        return this.userRepository.findUserByEmail(user.getEmail());
    }
}
