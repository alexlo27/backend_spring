package com.alexlo.msvc_user.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = PermissionEntity.class)
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEntity role)) return false;
        return id != null && id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "RoleEntity{id=" + id + ", name='" + name + "'}";
    }

}
