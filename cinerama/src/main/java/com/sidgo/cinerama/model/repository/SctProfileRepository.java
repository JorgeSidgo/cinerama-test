package com.sidgo.cinerama.model.repository;

import com.sidgo.cinerama.model.dto.SctProfileDTO;
import com.sidgo.cinerama.model.entity.SctProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SctProfileRepository extends JpaRepository<SctProfile, Long> {

    @Query("select new com.sidgo.cinerama.model.dto.SctProfileDTO(p.id, p.displayName) from SctProfile p")
    List<SctProfileDTO> getActiveProfiles();

}
