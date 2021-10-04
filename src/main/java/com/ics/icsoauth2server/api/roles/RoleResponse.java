package com.ics.icsoauth2server.api.roles;

import com.ics.icsoauth2server.domain.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class RoleResponse {
    private Long id;
    private String uuid;
    private String role;
    private String description;
    private List<Permissions> permissions;
}
