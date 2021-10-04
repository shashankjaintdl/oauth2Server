package com.ics.icsoauth2server.api.roles.services;

import com.ics.icsoauth2server.api.permissions.repository.PermissionRepository;
import com.ics.icsoauth2server.domain.Permissions;
import com.ics.icsoauth2server.domain.Roles;
import com.ics.icsoauth2server.api.roles.RolesRequest;
import com.ics.icsoauth2server.api.roles.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService{

    private final RolesRepository rolesRepository;
    private final IdGenerator idGenerator;
    private final PermissionRepository permissionRepository;

//    @PostConstruct
//    private void postConstruct() {
//
//        Permissions permission1 = new Permissions(idGenerator.generateId().toString(), "read:name", "READ Permission");
//
//        permissionRepository.save(permission1);
//        Permissions permission2 = new Permissions(idGenerator.generateId().toString(), "READ", "READ Permission");
//        permissionRepository.save(permission2);
//        rolesRepository.save(new Roles(idGenerator.generateId().toString(),"ROLE_AD","Admin Roles"));
//        rolesRepository.save(new Roles(idGenerator.generateId().toString(),"ROLE_USER","USER"));
//        rolesRepository.save(new Roles(idGenerator.generateId().toString(),"ROLE_MGR","MANAGER"));
//
//        Roles roles = rolesRepository.findById(1L).get();
//        List<Permissions> permissions1 = new ArrayList<>();
//        permissions1.add(permission1);
//        permissions1.add(permission2);
//        roles.setPermissions(permissions1.stream().collect(Collectors.toSet()));
//        rolesRepository.save(roles);
////
//    }

    @Override
    public Roles addNewRole(RolesRequest request) {
        return null;
    }

    @Override
    public Roles updateRole(Long id, RolesRequest rolesRequest) {
        return null;
    }

    @Override
    public Roles findRole(Long id) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public List<Roles> listRoles() {
        return rolesRepository.findAll();
    }
}
