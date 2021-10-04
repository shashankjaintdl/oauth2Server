package com.ics.icsoauth2server.api.permissions.service;

import com.ics.icsoauth2server.api.permissions.repository.PermissionRepository;
import com.ics.icsoauth2server.domain.Permissions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService{

    private final PermissionRepository permissionRepository;
    private final IdGenerator idGenerator;

    @PostConstruct
    public void postConstruct(){

    }

    @Override
    public List<Permissions> getAllPermission() {


        return null;
    }
}
