package com.sidgo.cinerama.controller;

import com.sidgo.cinerama.model.dto.AuthenticationDTO;
import com.sidgo.cinerama.model.dto.AuthenticationRequestDTO;
import com.sidgo.cinerama.model.dto.ResponseDTO;
import com.sidgo.cinerama.model.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO auth) {
        ResponseDTO response = new ResponseDTO();
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        HttpStatus status;
        try {
            response.setCode(ResponseDTO.COD_AUTH_OK);
            response.setMessage(ResponseDTO.MSG_AUTH_OK);
            response.setResult(authService.authenticate(auth));
            status = HttpStatus.OK;
        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            status = HttpStatus.UNAUTHORIZED;
            response.setCode(ResponseDTO.COD_AUTH_DND);
            response.setMessage(ResponseDTO.MSG_AUTH_DND);
        }

        return new ResponseEntity<>(response, status);
    }
}
