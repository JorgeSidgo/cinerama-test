package com.sidgo.cinerama.model.repository;

import com.sidgo.cinerama.model.dto.SctUserDTO;
import com.sidgo.cinerama.model.entity.SctUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SctUserRepository extends JpaRepository<SctUser, Long> {

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctUserDTO(u.id, u.userName, u.firstNames, u.lastNames, u.email, u.profile.name, u.profile.id, u.profile.displayName, u.state) from SctUser u where u.state = 1 or u.state = 2")
    public List<SctUserDTO> getActiveUsers();

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctUserDTO(u.id, u.userName, u.firstNames, u.lastNames, u.email, u.profile.name, u.profile.id, u.profile.displayName, u.state) from SctUser u where u.state = 0")
    public List<SctUserDTO> getInactiveUsers();

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctUserDTO(u.id, u.userName, u.firstNames, u.lastNames, u.email, u.profile.name, u.profile.id, u.profile.displayName, u.pwd, u.state) from SctUser u where u.userName = :username")
    public SctUserDTO authUser(@Param("username") String username);

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctUserDTO(u.id, u.userName, u.firstNames, u.lastNames, u.email, u.profile.name, u.profile.id, u.profile.displayName, u.state) from SctUser u where u.state = 1 or u.state = 2")
    Page<SctUserDTO> getActiveUsers(Pageable pageable);
}
