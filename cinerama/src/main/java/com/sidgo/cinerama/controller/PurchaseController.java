package com.sidgo.cinerama.controller;

import com.sidgo.cinerama.model.dto.ResponseDTO;
import com.sidgo.cinerama.model.dto.WkfPurchaseDTO;
import com.sidgo.cinerama.model.entity.WkfPurchase;
import com.sidgo.cinerama.model.service.WkfPurchaseService;
import com.sidgo.cinerama.model.specification.purchase.PurchaseWithMovie;
import com.sidgo.cinerama.model.specification.purchase.PurchaseWithPurchaseType;
import com.sidgo.cinerama.model.specification.purchase.PurchaseWithState;
import com.sidgo.cinerama.model.specification.purchase.PurchaseWithUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/purchase")
public class PurchaseController {

    @Autowired
    WkfPurchaseService wkfPurchaseService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPurchases(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "type", required = false) long type,
            @RequestParam(name = "state", required = false) long state,
            @RequestParam(name = "movie", required = false) String movie
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        Specification<WkfPurchase> spec = Specification
                .where(new PurchaseWithPurchaseType(type))
                .and(new PurchaseWithUsername(username))
                .and(new PurchaseWithMovie(movie))
                .and(new PurchaseWithState(state));

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(wkfPurchaseService.getPurchases(spec, page, size, sort));
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
    public ResponseEntity<ResponseDTO> save(
        @RequestBody WkfPurchaseDTO purchase
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(wkfPurchaseService.savePurchase(purchase));
            status = HttpStatus.CREATED;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            response.setErrors(ex.getMessage().toString());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<ResponseDTO> returnMovie(
            @PathVariable long id
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(wkfPurchaseService.returnMovie(id));
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
