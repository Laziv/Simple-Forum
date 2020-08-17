package ua.lviv.lgs.nl.simpleforum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.lviv.lgs.nl.simpleforum.dto.UserDTO;
import ua.lviv.lgs.nl.simpleforum.entity.Role;
import ua.lviv.lgs.nl.simpleforum.entity.User;
import ua.lviv.lgs.nl.simpleforum.exception.ResourceNotFoundException;
import ua.lviv.lgs.nl.simpleforum.repository.RoleRepository;
import ua.lviv.lgs.nl.simpleforum.repository.UserRepository;
import ua.lviv.lgs.nl.simpleforum.service.UserService;
import ua.lviv.lgs.nl.simpleforum.service.mapper.UserMapper;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return Optional.ofNullable(userMapper.toDto(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"))));
    }

    @Override
    public void createUser(UserDTO userDTO) {
        Role role;
        if (userRepository.count() == 0){
            role = roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new ResourceNotFoundException("not found role ROLE_ADMIN"));
        }else {
            role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("not found role ROLE_USER"));
        }

        User user = userMapper.toEntity(userDTO);
        user.getRoles().add(role);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean isEmailAlreadyInUse(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean isUsernameAlreadyInUse(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


}
