package ru.clevertec.businesslogic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.businesslogic.dto.CreateUserDTO;
import ru.clevertec.businesslogic.entity.User;
import ru.clevertec.businesslogic.exception.UserNotFoundException;
import ru.clevertec.businesslogic.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserDTO createUserDTO) {
        User createdUser = User.builder()
                .username(createUserDTO.getUsername())
                .email(createUserDTO.getEmail())
                .build();
        return userRepository.save(createdUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User with name = " + username + " was not found"));
    }

    @Override
    public User changeEmail(Long userId, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id = " + userId + "was not found"));
        user.setEmail(email);

        return userRepository.save(user);
    }
}
