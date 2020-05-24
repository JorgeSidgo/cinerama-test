package com.sidgo.cinerama.model.repository;

import com.sidgo.cinerama.model.dto.SctPermissionDTO;
import com.sidgo.cinerama.model.entity.SctPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SctPermissionRepository extends JpaRepository<SctPermission, Long> {

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctPermissionDTO(p.id, p.name, p.displayName, p.icon, p.route, p.state, p.orderNumber, p.father, p.family) from SctPermission p, SctProfilePermission pp where pp.profile.name = :role and p.id = pp.permission.id and p.state = 1 order by p.orderNumber asc ")
    public List<SctPermissionDTO> getActiveSctPermissionByProfile(@Param("role") String role);

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctPermissionDTO(p.id, p.name, p.displayName, p.icon, p.route, p.state, p.orderNumber, p.father, p.family) from SctPermission p join SctProfilePermission pp where pp.profile.name = :role and p.state = 0 order by p.orderNumber asc ")
    public List<SctPermissionDTO> getInactiveSctPermissionByProfile(@Param("role") String role);

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctPermissionDTO(p.id, p.name, p.displayName, p.icon, p.route, p.state, p.orderNumber, p.father, p.family) from SctPermission p join SctProfilePermission pp where pp.profile.name = :role and p.state = 1 order by p.orderNumber asc ")
    public List<SctPermissionDTO> getAllSctPermissionByProfile(@Param("role") String role);

}
