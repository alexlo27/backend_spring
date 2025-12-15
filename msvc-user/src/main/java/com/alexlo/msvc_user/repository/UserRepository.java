package com.alexlo.msvc_user.repository;

import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.model.UserEntity;
import com.alexlo.msvc_user.repository.projection.UserProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u JOIN FETCH u.roles r")
    List<UserEntity> findAllWithRoles();

    @Query("""
       SELECT u.id 
       FROM UserEntity u 
       WHERE (:username IS NULL OR :username = '' OR LOWER(u.username) LIKE CONCAT('%', :username, '%'))
       """)
    Page<Long> findAllIds(@Param("username") String username, Pageable pageable);

    @EntityGraph(attributePaths = {"roles"})
    @Query("SELECT u FROM UserEntity u WHERE u.id IN :ids")
    List<UserEntity> findAllByIdInWithRoles(@Param("ids") List<Long> ids);

    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<UserEntity> findByUsername(String username);
}
