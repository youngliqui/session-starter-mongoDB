package ru.clevertec.businesslogic.service;

import ru.clevertec.businesslogic.dto.CreateUserDTO;
import ru.clevertec.businesslogic.entity.User;

import java.util.List;

public interface UserService {
    User createUser(CreateUserDTO createUserDTO);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    User changeEmail(Long userId, String email);
}
