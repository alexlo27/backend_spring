package com.alexlo.msvc_user.service;

import com.alexlo.msvc_user.model.UserEntity;
import com.alexlo.msvc_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(">>> Cargando usuario desde UserDetailsService");
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario "+username+" no existe."));

        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream().
                map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName())))
                .collect(Collectors.toSet());

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getIsEnabled(),
                userEntity.getAccountNoExpired(),
                userEntity.getCredentialNoExpired(),
                userEntity.getAccountNoLocked(),
                authorities);
    }
}
