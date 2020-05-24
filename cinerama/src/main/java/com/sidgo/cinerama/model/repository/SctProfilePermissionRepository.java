package com.sidgo.cinerama.model.repository;

import com.sidgo.cinerama.model.entity.SctProfilePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SctProfilePermissionRepository extends JpaRepository<SctProfilePermission, Long> {
}
