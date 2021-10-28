package com.ics.icsoauth2server.api.roles.services;

import com.ics.icsoauth2server.domain.Roles;
import com.ics.icsoauth2server.api.roles.RolesRequest;

import java.util.List;
import java.util.Set;

public interface RolesService {
    Roles addNewRole(RolesRequest request);
    Roles updateRole(Long id,RolesRequest rolesRequest);
    Roles findRole(Long id);
    void deleteRole(Long id);
    List<Roles> listRoles();
    Set<Roles> getUserRolesById(Long id);
}
