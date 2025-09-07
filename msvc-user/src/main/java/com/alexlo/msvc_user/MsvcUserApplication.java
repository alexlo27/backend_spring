package com.alexlo.msvc_user;

import com.alexlo.msvc_user.model.*;
import com.alexlo.msvc_user.repository.RoleRepository;
import com.alexlo.msvc_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Set;

@SpringBootApplication
public class MsvcUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcUserApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Bean
	CommandLineRunner init(){
		return args -> {

			/*RoleEntity roleAdmin = RoleEntity.builder()
					.name("Admin")
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.name("User")
					.build();

			roleRepository.save(roleAdmin);
			roleRepository.save(roleUser);

			UserEntity userEntity = UserEntity.builder()
					.email("alexlo@gmail.com")
					.username("alexlo")
					.password(passwordEncoder.encode("123456"))
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.isEnabled(true)
					.roles(Set.of(roleAdmin,roleUser))
					.build();
			userRepository.save(userEntity);

			UserEntity userEntity2 = UserEntity.builder()
					.email("marco@gmail.com")
					.username("marco")
					.password(passwordEncoder.encode("123456"))
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.isEnabled(true)
					.roles(Set.of(roleUser))
					.build();
			userRepository.save(userEntity2);

			UserEntity userEntity3 = UserEntity.builder()
					.email("wiliam@gmail.com")
					.username("wiliam")
					.password(passwordEncoder.encode("123456"))
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.isEnabled(true)
					.roles(Set.of(roleUser))
					.build();
			userRepository.save(userEntity3);*/
		};
	}

}
