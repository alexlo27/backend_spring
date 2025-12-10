package com.alexlo.msvc_user.repository.projection;

import java.util.Set;

public interface UserProjection {

    Long getId();
    String getEmail();
    String getUsername();
    Boolean getIsEnabled();
    Set<RoleInfo> getRoles();


    interface RoleInfo {
        String getName();
    }
}
