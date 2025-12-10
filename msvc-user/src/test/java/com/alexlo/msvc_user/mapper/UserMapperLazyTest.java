package com.alexlo.msvc_user.mapper;

import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.mappers.UserMapper;
import com.alexlo.msvc_user.model.RoleEntity;
import com.alexlo.msvc_user.model.UserEntity;
import com.alexlo.msvc_user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnitUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Le indica a Spring que cargue el contexto completo
@Transactional // Mantiene la sesión de Hibernate para LAZY
public class UserMapperLazyTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testLazyLoadingPermissions() {
        // Traemos un usuario
        UserEntity user = userRepository.findById(1L).orElseThrow();

        PersistenceUnitUtil util = entityManager.getEntityManagerFactory().getPersistenceUnitUtil();

        // Verificamos antes de mapear
        System.out.println("Roles cargados antes del mapper? " + util.isLoaded(user, "roles"));

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            RoleEntity firstRole = user.getRoles().iterator().next();
            System.out.println("Permisos cargados antes del mapper? " +
                    util.isLoaded(firstRole, "permissions"));
        }

        // Aplicamos el mapper
        UserResponseDTO dto = userMapper.toResponseDetail(user);

        // Verificamos después del mapper
        System.out.println("Roles cargados después del mapper? " + util.isLoaded(user, "roles"));

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            RoleEntity firstRole = user.getRoles().iterator().next();
            System.out.println("Permisos cargados después del mapper? " +
                    util.isLoaded(firstRole, "permissions"));
        }

        // Opcional: imprimir DTO
        System.out.println(dto);
    }
}
