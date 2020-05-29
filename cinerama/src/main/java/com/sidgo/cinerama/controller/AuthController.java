package com.sidgo.cinerama.controller;

import com.sidgo.cinerama.model.dto.AuthenticationRequestDTO;
import com.sidgo.cinerama.model.dto.ResponseDTO;
import com.sidgo.cinerama.model.service.AuthService;
import com.sidgo.cinerama.util.ValidationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    ValidationHandler validationHandler;

    @PostMapping("/auth")
    public ResponseEntity<ResponseDTO> authenticate(
            @Valid @RequestBody AuthenticationRequestDTO auth
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;
        try {
            response.setCode(ResponseDTO.COD_AUTH_OK);
            response.setMessage(ResponseDTO.MSG_AUTH_OK);
            response.setResult(authService.authenticate(auth));
            status = HttpStatus.OK;
        }
        catch (ConstraintViolationException ex) {
            return validationHandler.handleValidationExceptions(ex);
        }
        catch (BadCredentialsException | UsernameNotFoundException ex) {
            status = HttpStatus.UNAUTHORIZED;
            response.setCode(ResponseDTO.COD_AUTH_DND);
            response.setMessage(ResponseDTO.MSG_AUTH_DND);
            response.setErrors(ex.getMessage());
        }

        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth != null) {
                new SecurityContextLogoutHandler().logout(req, res, auth);
            }

            response.setCode(ResponseDTO.COD_AUTH_OK);
            response.setMessage(ResponseDTO.MSG_AUTH_OK);
            response.setResult("Logged out");
            status = HttpStatus.OK;
        } catch (Exception ex) {
            status = HttpStatus.UNAUTHORIZED;
            response.setCode(ResponseDTO.COD_AUTH_DND);
            response.setMessage(ResponseDTO.MSG_AUTH_DND);
        }

        return new ResponseEntity<>(response, status);
    }
}
