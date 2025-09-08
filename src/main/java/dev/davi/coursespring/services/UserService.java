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
    public User insert(User obj){
        return userRepository.save(obj);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
    public User update(Long id, User obj){
        User entity = userRepository.getReferenceById(id);
        updateData(entity, obj);
        return userRepository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}