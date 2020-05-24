package com.sidgo.cinerama.model.service;

import com.sidgo.cinerama.model.dto.AuthenticationRequestDTO;

public interface AuthService {

    /**
     * This method is used to perform a Custom Spring Security Authentication
     * @param authRequestDTO This is the first paramter to addNum method
     * @return String returns JWT String in case of successfulAuthentication
     */
    public String authenticate(AuthenticationRequestDTO authRequestDTO);

}
