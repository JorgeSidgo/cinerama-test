package com.sidgo.cinerama.util;

import com.sidgo.cinerama.model.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationHandler {

    public ResponseEntity<ResponseDTO> handleValidationExceptions(
            ConstraintViolationException ex) {

        ResponseDTO resp = new ResponseDTO();
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.put(

                    violation.getPropertyPath().toString(), violation.getMessage()
            );
        }

        resp.setCode(ResponseDTO.COD_ERR_VAL);
        resp.setMessage(ResponseDTO.MSG_ERR_VAL);
        resp.setErrors(errors);

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

}
