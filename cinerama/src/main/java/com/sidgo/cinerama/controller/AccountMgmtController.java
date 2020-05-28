package com.sidgo.cinerama.controller;

import com.sidgo.cinerama.model.dto.ResponseDTO;
import com.sidgo.cinerama.model.service.SctUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountMgmtController {

    @Autowired
    SctUserService sctUserService;

    @GetMapping("/confirm/{userCode}")
    private String confirmUserAccount(
            @PathVariable("userCode") String userCode
    ) {
        try {
            return sctUserService.confirmAccount(userCode);
        } catch (Exception ex) {
            return "Error";
        }
    }

    @GetMapping("/forgot/{email}")
    private ResponseEntity<ResponseDTO> recoverPassword(
            @PathVariable("email") String email
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(sctUserService.forgotPassword(email));
            status = HttpStatus.OK;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            response.setErrors(ex.getMessage().toString());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

}
