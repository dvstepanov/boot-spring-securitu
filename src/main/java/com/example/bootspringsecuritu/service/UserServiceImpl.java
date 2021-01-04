package com.example.bootspringsecuritu.service;

import com.example.bootspringsecuritu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.bootspringsecuritu.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        Iterable<User> users = userRepository.findAll();
        return (List<User>) users;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).get();
    }
}
