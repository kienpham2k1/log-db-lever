package com.example.logdblever.service;

import com.example.logdblever.model.User;
import org.springframework.stereotype.Service;
import com.example.logdblever.repo.UserRepo;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(int id) {
        return userRepo.findById(id).get();
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public User update(User user) {
        return userRepo.save(user);
    }

    public void delete(Integer id) {
        userRepo.deleteById(id);
    }
}
