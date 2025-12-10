package com.alexlo.msvc_user.service;

import com.alexlo.msvc_user.dto.request.CreateUserDTO;
import com.alexlo.msvc_user.dto.response.PageResponse;
import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.exception.NotFoundException;
import com.alexlo.msvc_user.mappers.PageMapper;
import com.alexlo.msvc_user.mappers.UserMaper;
import com.alexlo.msvc_user.mappers.UserMapper;
import com.alexlo.msvc_user.model.RoleEntity;
import com.alexlo.msvc_user.model.UserEntity;
import com.alexlo.msvc_user.repository.RoleRepository;
import com.alexlo.msvc_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
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
                .isEnabled(dto.isEnabled())
                .roles(rolesEntity)
                .build();
        return userMapper.toResponseDetail(userRepository.save(userEntity));
    }

    @Override
    public UserResponseDTO update(CreateUserDTO dto) {

        UserEntity userEntity = getUserById(dto.id());
        Set<String> roles = Optional.ofNullable(dto.roles()).orElse(Collections.emptySet());
        Set<RoleEntity> rolesEntity = resolveRoles(roles);
        if (dto.email() != null && !dto.email().isBlank()) userEntity.setEmail(dto.email());
        if (dto.password() != null && !dto.password().isBlank()) userEntity.setPassword(passwordEncoder.encode(dto.password()));
        if (dto.isEnabled() != null) userEntity.setIsEnabled(dto.isEnabled());
        userEntity.setRoles(rolesEntity);
        //userMapper.updateEntityFromDto(dto, userEntity);
        return userMapper.toResponseDetail(userRepository.save(userEntity));
    }

    @Override
    public UserResponseDTO findById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                                    .orElseThrow(() -> new NotFoundException("Usuario no existe"));
        return userMapper.toResponseDetail(userEntity);
    }

    @Override
    public List<UserResponseDTO> all() {
        return userMapper.toResponseListBasic(userRepository.findAll());
    }

    @Override
    public List<UserResponseDTO> allWithRoles() {
        return userMapper.toResponseListDetail(userRepository.findAllWithRoles());
    }

    @Override
    public PageResponse<UserResponseDTO> allWithRoles(Pageable pageable) {
        //return PageMapper.map(userRepository.findAllBy(pageable),userMapper::toResponseDetail);

        Page<Long> pageOfIds = userRepository.findAllIds(pageable);

        if (pageOfIds.isEmpty()) {
            return PageMapper.map(Page.empty(pageable), userMapper::toResponseDetail);
        }


        List<UserEntity> users =
                userRepository.findAllByIdInWithRoles(pageOfIds.getContent());

        // (Opcional) Mantener el orden original de paginación
        Map<Long, UserEntity> mapUsers = users.stream()
                .collect(Collectors.toMap(UserEntity::getId, u -> u));

        List<UserEntity> orderedUsers = pageOfIds.getContent().stream()
                .map(mapUsers::get)
                .collect(Collectors.toList());

        // Mapear con tu mapper y devolver respuesta paginada
        Page<UserEntity> finalPage =
                new PageImpl<>(orderedUsers, pageable, pageOfIds.getTotalElements());

        return PageMapper.map(finalPage, userMapper::toResponseDetail);
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
