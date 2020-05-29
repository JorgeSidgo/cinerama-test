package com.sidgo.cinerama.model.repository;

import com.sidgo.cinerama.model.dto.SctUserDTO;
import com.sidgo.cinerama.model.entity.SctUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SctUserRepository extends BaseRepository<SctUser, Long> {

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctUserDTO(u.id, u.code, u.userName, u.firstNames, u.lastNames, u.email, u.profile.name, u.profile.id, u.profile.displayName, u.pwd, u.state, u.createdAt) from SctUser u where u.userName = :username and u.state = 1")
    public SctUserDTO getUserByUsername(@Param("username") String username);

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctUserDTO(u.id, u.code, u.userName, u.firstNames, u.lastNames, u.email, u.profile.name, u.profile.id, u.profile.displayName, u.pwd, u.state, u.createdAt) from SctUser u where u.email = :email")
    public SctUserDTO getUserByEmail(@Param("email") String email);

    @Query("select u from SctUser u where u.email = :email")
    public SctUser getUserEntityByEmail(@Param("email") String email);

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctUserDTO(u.id, u.code, u.userName, u.firstNames, u.lastNames, u.email, u.profile.name, u.profile.id, u.profile.displayName, u.pwd, u.state, u.createdAt) from SctUser u where u.code = :code")
    public SctUserDTO getUserByCode(@Param("code") String code);

    @Query("select u from SctUser u where u.code = :code")
    public SctUser getUserEntityByCode(@Param("code") String code);

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctUserDTO(u.id, u.code, u.userName, u.firstNames, u.lastNames, u.email, u.profile.name, u.profile.id, u.profile.displayName, u.pwd, u.state, u.createdAt) from SctUser u where u.id = :id")
    public SctUserDTO getUserById(@Param("id") long id);

    @Query("SELECT new com.sidgo.cinerama.model.dto.SctUserDTO(u.id, u.userName, u.firstNames, u.lastNames, u.email, u.profile.id, u.profile.displayName, u.state, u.createdAt) from SctUser u where u.id = :id and u.state = 1 or u.state = 2")
    public SctUserDTO getUserByIdShow(@Param("id") long id);

}
