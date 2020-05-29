package com.sidgo.cinerama.model.service;

import com.sidgo.cinerama.model.dto.WkfPurchaseDTO;
import com.sidgo.cinerama.model.entity.WkfPurchase;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface WkfPurchaseService {

    Page<WkfPurchaseDTO> getPurchases(Specification<WkfPurchase> spec, int page, int size, String sort);

    WkfPurchaseDTO savePurchase(WkfPurchaseDTO purchaseDTO) throws UnsupportedEncodingException, MessagingException;

    WkfPurchaseDTO returnMovie(long id) throws Exception;
}
