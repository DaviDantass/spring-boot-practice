package dev.davi.coursespring.services;

import dev.davi.coursespring.entities.User;
import dev.davi.coursespring.exceptions.UserNotFoundException;
import dev.davi.coursespring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found. Id: " + id));
    }
}