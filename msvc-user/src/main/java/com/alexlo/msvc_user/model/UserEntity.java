package com.alexlo.msvc_user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Size(max = 80)
    private String email;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    private String password;

    @Builder.Default
    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    @Builder.Default
    @Column(name = "account_no_expired")
    private Boolean accountNoExpired = true;

    @Builder.Default
    @Column(name = "account_no_locked")
    private Boolean accountNoLocked = true;

    @Builder.Default
    @Column(name = "credential_no_expired")
    private Boolean credentialNoExpired = true;

    @ManyToMany( fetch = FetchType.LAZY, targetEntity = RoleEntity.class)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    @Override
    public boolean equals(Object o){
        if( this == o) return true;
        if( !(o instanceof UserEntity user)) return false;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", accountNoExpired=" + accountNoExpired +
                ", accountNoLocked=" + accountNoLocked +
                ", credentialNoExpired=" + credentialNoExpired +
                '}';
    }
}
