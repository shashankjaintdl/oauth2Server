package com.ics.icsoauth2server.api.roles.repository;

import com.ics.icsoauth2server.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
}
