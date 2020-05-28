package com.sidgo.cinerama.controller;

import com.sidgo.cinerama.model.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/purchase")
public class PurchaseController {



    @GetMapping
    public ResponseEntity<ResponseDTO> getPurchases(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "role", required = false) String role
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
//            response.setCode(ResponseDTO.COD_OK);
//            response.setMessage(ResponseDTO.MSG_OK);
//            response.setResult(sctUserService.getUsers(spec, page, size, sort));
            status = HttpStatus.OK;
        }

        catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            response.setErrors(ex.getMessage().toString());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

}
