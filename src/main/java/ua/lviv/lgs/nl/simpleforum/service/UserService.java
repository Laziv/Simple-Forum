package ua.lviv.lgs.nl.simpleforum.service;

import ua.lviv.lgs.nl.simpleforum.dto.UserDTO;
import ua.lviv.lgs.nl.simpleforum.entity.User;

import java.util.Optional;


public interface UserService {
    Optional<UserDTO> findByUsername(String username);

    void createUser(UserDTO userDTO);

    boolean isEmailAlreadyInUse(String email);

    boolean isUsernameAlreadyInUse(String username);


}
