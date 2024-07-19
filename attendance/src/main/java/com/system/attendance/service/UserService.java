package com.system.attendance.service;

import com.system.attendance.model.User;
import com.system.attendance.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email is already taken");
        }
        return userRepository.save(user);
    }

    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid email or password");
    }
    public User addUser(User user) {

        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    public User updateUser(User user) {
        User existUser = userRepository.findById(user.getId()).get();
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setPassword(user.getPassword());
        User updateUser = userRepository.save(existUser);
        return updateUser;
    }

    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }


    public User getUserById(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }
}
