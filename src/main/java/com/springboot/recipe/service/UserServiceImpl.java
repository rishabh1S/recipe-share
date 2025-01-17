package com.springboot.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.recipe.exception.DuplicateRecordException;
import com.springboot.recipe.model.User;
import com.springboot.recipe.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public User createUser(User user) {
        User existingUser = userRepo.findByUsername(user.getUsername()).orElse(null);
        if (existingUser != null) {
            throw new DuplicateRecordException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User updateUser(long id, User userDetails) {
        Optional<User> getUser = userRepo.findById(id);
        if (getUser.isPresent()) {
            User user = getUser.get();
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setUserRole(userDetails.getUserRole());
            return userRepo.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        }
    }

    @Override
    public List<User> getUserByRole(String userRole) {
        return userRepo.findByUserRole(userRole).orElse(List.of());
    }
}
