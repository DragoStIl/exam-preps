package com.prep.battleShips.services;

import com.prep.battleShips.entities.User;
import com.prep.battleShips.entities.dto.UserLoginDTO;
import com.prep.battleShips.entities.dto.UserRegistrationDTO;
import com.prep.battleShips.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(UserRegistrationDTO registrationDTO) {
        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            return false;
        }

        Optional<User> usernameCheck = userRepository.findByUsername(registrationDTO.getUsername());
        Optional<User> emailCheck = userRepository.findByEmail(registrationDTO.getEmail());

        if (usernameCheck.isPresent()){
            return false;
        }

        if (emailCheck.isPresent()){
            return false;
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setFullName(registrationDTO.getFullName());
        user.setPassword(registrationDTO.getPassword());
        user.setEmail(registrationDTO.getEmail());
        this.userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        return false;
    }
}
