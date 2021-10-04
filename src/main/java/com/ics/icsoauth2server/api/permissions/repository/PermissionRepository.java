package com.ics.icsoauth2server.api.permissions.repository;

import com.ics.icsoauth2server.domain.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long>{
}
