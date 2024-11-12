package com.outhelix.service;

import com.outhelix.model.User;
import com.outhelix.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByEmailOrUsername(String loginEmailOrUsername) {
        if (isEmail(loginEmailOrUsername)) {
            return userRepository.findByEmailOrUsername(loginEmailOrUsername, null);
        } else {
            return userRepository.findByEmailOrUsername(null, loginEmailOrUsername);
        }
    }

    private boolean isEmail(String str) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
