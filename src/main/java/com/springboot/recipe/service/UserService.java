package com.springboot.recipe.service;

import java.util.List;
import com.springboot.recipe.model.User;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserById(long id);

    public User findByUsername(String username);

    public User createUser(User user);

    public User updateUser(long id, User userDetails);

    public void deleteUser(long id);

    public List<User> getUserByRole(String userRole);
}
