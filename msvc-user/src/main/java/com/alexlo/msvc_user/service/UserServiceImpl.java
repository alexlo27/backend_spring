package com.alexlo.msvc_user.service;

import com.alexlo.msvc_user.dto.request.CreateUserDTO;
import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.exception.NotFoundException;
import com.alexlo.msvc_user.mappers.UserMapper;
import com.alexlo.msvc_user.model.RoleEntity;
import com.alexlo.msvc_user.model.UserEntity;
import com.alexlo.msvc_user.repository.RoleRepository;
import com.alexlo.msvc_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDTO create(CreateUserDTO dto) {
        Set<String> roles = Optional.ofNullable(dto.roles()).orElse(Collections.emptySet());
        Set<RoleEntity> rolesEntity = resolveRoles(roles);

        UserEntity userEntity =  UserEntity.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .email(dto.email())
                .roles(rolesEntity)
                .build();
        return userMapper.toResponse(userRepository.save(userEntity));
    }

    @Override
    public UserResponseDTO update(CreateUserDTO dto) {

        UserEntity userEntity = getUserById(dto.id());
        Set<String> roles = Optional.ofNullable(dto.roles()).orElse(Collections.emptySet());
        Set<RoleEntity> rolesEntity = resolveRoles(roles);
        if (dto.email() != null) userEntity.setEmail(dto.email());
        if (dto.password() != null) userEntity.setPassword(dto.password());
        userEntity.setRoles(rolesEntity);
        //userMapper.updateEntityFromDto(dto, userEntity);
        return userMapper.toResponse(userRepository.save(userEntity));
    }

    @Override
    public UserResponseDTO findById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                                    .orElseThrow(() -> new NotFoundException("Usuario no existe"));
        return userMapper.toResponse(userEntity);
    }

    @Override
    public List<UserResponseDTO> all() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        getUserById(id);
        userRepository.deleteById(id);
    }

    private UserEntity getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Usuario no existe"));
    }

    private Set<RoleEntity> resolveRoles(Set<String> roleNames) {
        return roleNames.stream()
                .map(role -> roleRepository.findByName(role)
                        .orElseThrow(() -> new NotFoundException("Rol no encontrado: " + role)))
                .collect(Collectors.toSet());
    }
}
