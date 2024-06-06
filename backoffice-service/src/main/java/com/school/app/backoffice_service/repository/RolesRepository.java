package com.school.app.backoffice_service.repository;

import com.school.app.backoffice_service.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,String> {
}
