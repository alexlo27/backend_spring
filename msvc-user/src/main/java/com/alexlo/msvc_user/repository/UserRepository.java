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

    @Query("SELECT u.id FROM UserEntity u")
    Page<Long> findAllIds(Pageable pageable);

    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    @Query("SELECT u FROM UserEntity u WHERE u.id IN :ids")
    List<UserEntity> findAllByIdInWithRoles(@Param("ids") List<Long> ids);

    /*@EntityGraph(attributePaths = {"roles"})
    @Override
    @NonNull
    Page<UserEntity> findAll(@NonNull Pageable pageable);*/

    /*@EntityGraph(attributePaths = {"roles"}, type = EntityGraph.EntityGraphType.FETCH)
    Page<UserEntity> findAllBy(Pageable pageable);*/


    /*@Query("""
        SELECT DISTINCT u FROM UserEntity u 
        LEFT JOIN FETCH u.roles r
        LEFT JOIN FETCH r.permissions p
        WHERE u.username = :username
    """)*/
    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<UserEntity> findByUsername(String username);
}
