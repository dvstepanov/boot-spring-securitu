package com.example.bootspringsecuritu.service;

import com.example.bootspringsecuritu.model.User;
import java.util.List;

public interface UserService {
    List<User> listUsers();
    User getUser(long id);
    void deleteUser(long id);
    void saveUser(User user);
    User getUserByEmail(String email);
}
