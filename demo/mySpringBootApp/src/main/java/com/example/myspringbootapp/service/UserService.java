package com.example.myspringbootapp.service;

import com.example.myspringbootapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> findAll() {
        return List.of();
    }

    public User findById(Long id) {
        return null;
    }

    public User create(User user) {
        return user;
    }

    public User update(User user) {
        return user;
    }

    public void deleteAll() {
    }
}
