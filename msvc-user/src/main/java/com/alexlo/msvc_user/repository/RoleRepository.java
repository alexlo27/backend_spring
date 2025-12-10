package com.alexlo.msvc_user.repository;

import com.alexlo.msvc_user.model.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    @EntityGraph(attributePaths = "permissions")
    @NonNull
    Page<RoleEntity> findAll(@NonNull Pageable pageable);

    Optional<RoleEntity> findByName(String name);
    boolean existsByName(String name);
}
