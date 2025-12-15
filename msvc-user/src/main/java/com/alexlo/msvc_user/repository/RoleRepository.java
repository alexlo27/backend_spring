package com.alexlo.msvc_user.repository;

import com.alexlo.msvc_user.model.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query("""
           SELECT r.id
           FROM RoleEntity r
           WHERE ( :name is null or :name = '' or LOWER(r.name) like CONCAT('%', :name, '%') )
           """)
    Page<Long> findAllIds(@Param("name") String name, Pageable pageable);

    @EntityGraph(attributePaths = "permissions")
    @Query("SELECT r FROM RoleEntity r WHERE r.id IN :ids")
    List<RoleEntity> findAllByIdWithPermission(@Param("ids") List<Long> ids);

    Optional<RoleEntity> findByName(String name);
    boolean existsByName(String name);
}
