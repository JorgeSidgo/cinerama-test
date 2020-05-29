package com.sidgo.cinerama.controller;

import com.sidgo.cinerama.model.dto.ResponseDTO;
import com.sidgo.cinerama.model.dto.SctUserDTO;
import com.sidgo.cinerama.model.entity.SctUser;
import com.sidgo.cinerama.model.exception.ExistingUserException;
import com.sidgo.cinerama.model.service.SctUserService;
import com.sidgo.cinerama.model.specification.user.*;
import com.sidgo.cinerama.util.ValidationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    SctUserService sctUserService;

    @Autowired
    ValidationHandler validationHandler;

    @GetMapping
    public ResponseEntity<ResponseDTO> getActiveUsers(
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
            Specification<SctUser> spec = Specification
                    .where(new UserActive())
                    .and(new UserWithUsername(username))
                    .and(new UserWithEmail(email))
                    .and(new UserWithName(name))
                    .and(new UserWithRole(role));

            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(sctUserService.getUsers(spec, page, size, sort));
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getActiveUser(
            @PathVariable("id") long id
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(sctUserService.getUser(id));
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


    @PostMapping
    public ResponseEntity<ResponseDTO> saveUser(
            @Valid @RequestBody SctUserDTO userDTO
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(sctUserService.save(userDTO));
            status = HttpStatus.CREATED;
        }
        catch (ExistingUserException ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ex.getMessage().toString());
            status = HttpStatus.CONFLICT;
        }
        catch (ConstraintViolationException ex) {
            return validationHandler.handleValidationExceptions(ex);
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

    @PutMapping("{id}/role/{idRole}")
    public ResponseEntity<ResponseDTO> changeUserRole(
            @PathVariable("id") long id,
            @PathVariable("idRole") long idRole
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(sctUserService.changeRole(id, idRole));
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
