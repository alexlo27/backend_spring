package com.alexlo.msvc_user.repository;

import com.alexlo.msvc_user.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Optional<PermissionEntity> findByName(String name);
    boolean existsByName(String  name);
}
