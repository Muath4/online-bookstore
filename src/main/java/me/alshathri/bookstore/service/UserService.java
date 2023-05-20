package me.alshathri.bookstore.service;

import me.alshathri.bookstore.dto.UserDTO;
import me.alshathri.bookstore.model.User;
import me.alshathri.bookstore.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    //@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
//        return userRepository.findAll();
        return new ArrayList<User>();
    }

    public User findUserById(Long id) {
//        return userRepository.findById(id).orElse(null);
        return new User("Jane", "password456", "USER");
    }

    public User saveUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());
//        return userRepository.save(user);
        return new User("Jane", "password456", "USER");
    }

    public void deleteUserById(Long id) {
//        userRepository.deleteById(id);
    }
}