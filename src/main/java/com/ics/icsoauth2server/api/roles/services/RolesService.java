package com.ics.icsoauth2server.api.roles.services;

import com.ics.icsoauth2server.domain.Roles;
import com.ics.icsoauth2server.api.roles.RolesRequest;

import java.util.List;

@Deprecated
public interface RolesService {
    Roles addNewRole(RolesRequest request);
    Roles updateRole(Long id,RolesRequest rolesRequest);
    Roles findRole(Long id);
    void deleteRole(Long id);
    List<Roles> listRoles();
}
