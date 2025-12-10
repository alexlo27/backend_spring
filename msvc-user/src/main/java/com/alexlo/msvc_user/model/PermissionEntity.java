package com.alexlo.msvc_user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;

    @Override
    public boolean equals(Object o) {
        if( this == o) return true;
        if( !(o instanceof  PermissionEntity permission)) return false;
        return id != null && id.equals(permission.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
