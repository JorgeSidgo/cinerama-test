package com.sidgo.cinerama.model.service.impl;

import com.sidgo.cinerama.model.dto.MailDTO;
import com.sidgo.cinerama.model.dto.SctUserDTO;
import com.sidgo.cinerama.model.dto.WkfPurchaseDTO;
import com.sidgo.cinerama.model.entity.*;
import com.sidgo.cinerama.model.repository.CtgMovieRepository;
import com.sidgo.cinerama.model.repository.SctUserRepository;
import com.sidgo.cinerama.model.repository.WkfPurchaseRepository;
import com.sidgo.cinerama.model.service.MailService;
import com.sidgo.cinerama.model.service.WkfPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WkfPurchaseServiceImpl implements WkfPurchaseService {

    @Autowired
    WkfPurchaseRepository wkfPurchaseRepository;

    @Autowired
    SctUserRepository sctUserRepository;

    @Autowired
    CtgMovieRepository ctgMovieRepository;

    @Autowired
    MailService mailService;

    @Override
    public Page<WkfPurchaseDTO> getPurchases(Specification<WkfPurchase> spec, int page, int size, String sort) {
        Page<WkfPurchase> purchases;
        Pageable pageable;

        pageable = getPageable(page, size, sort);
        purchases = wkfPurchaseRepository.findAll(spec, pageable);

        return purchases.map(WkfPurchaseDTO::fromEntity);
    }

    @Override
    public WkfPurchaseDTO savePurchase(WkfPurchaseDTO purchaseDTO) throws UnsupportedEncodingException, MessagingException {
        WkfPurchase wkfPurchase = new WkfPurchase();
        WkfPurchaseDTO wkfPurchaseDTO = new WkfPurchaseDTO();
        String mailTemplate = "";
        String mailAction = "";
        BigDecimal finalPrice = null;
        Map<String, Object> model = new HashMap();

        try {
            Object username = (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            SctUserDTO user = sctUserRepository.getUserByUsername(username.toString());
            CtgMovie movieDTO = ctgMovieRepository.getById(purchaseDTO.getIdMovie());

            if (movieDTO.getAvailability().getId() != 1) {
                return null;
            }

            wkfPurchase.setMovie(new CtgMovie(purchaseDTO.getIdMovie()));
            wkfPurchase.setUser(new SctUser(user.getId()));
            wkfPurchase.setPurchaseType(new CtgPurchaseType(purchaseDTO.getPurchaseTypeId()));
            wkfPurchase.setCreatedAt(new Date());

            if (purchaseDTO.getPurchaseTypeId() == 1) {

                wkfPurchase.setTotal(movieDTO.getSalePrice().setScale(2, RoundingMode.HALF_EVEN));
                wkfPurchase.setPurchaseState(new CtgPurchaseState(1));
                finalPrice = wkfPurchase.getTotal();
                mailTemplate = "purchase.html";
                mailAction = MailDTO.PURCHASE;
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, 7);


                wkfPurchase.setEstimatedReturn(cal.getTime());
                wkfPurchase.setPurchaseState(new CtgPurchaseState(2));
                finalPrice = movieDTO.getRentalPrice().setScale(2, RoundingMode.HALF_EVEN);
                model.put("returnDate", new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
                mailTemplate = "rent.html";
                mailAction = MailDTO.RENT;
            }

            movieDTO.setStock(movieDTO.getStock() - 1);
            if (movieDTO.getStock() < 1) {
                movieDTO.setAvailability(new CtgAvailability(2));
            }
            movieDTO = ctgMovieRepository.save(movieDTO);
            wkfPurchaseDTO = WkfPurchaseDTO.fromEntity(wkfPurchaseRepository.save(wkfPurchase));

            MailDTO mailDTO = new MailDTO();
            model.put("name", user.getFirstNames() + " " + user.getLastNames());
            model.put("movie", movieDTO.getTitle());
            model.put("price", finalPrice);
            mailDTO.setTo(user.getEmail());
            mailDTO.setModel(model);

            mailService.sendMail(mailDTO, mailTemplate, mailAction);

        } catch (Exception exception) {
            throw exception;
        }

        return wkfPurchaseDTO;
    }

    @Override
    public WkfPurchaseDTO returnMovie(long id) throws Exception {
        WkfPurchase wkfPurchase = new WkfPurchase();
        WkfPurchaseDTO wkfPurchaseDTO = new WkfPurchaseDTO();
        CtgMovie movie = new CtgMovie();

        try {
            wkfPurchase = wkfPurchaseRepository.getOne(id);
            wkfPurchase.setPurchaseState(new CtgPurchaseState(1));


            if (wkfPurchase.getPurchaseType().getId() == 1) {
                throw new Exception("This purchase is not a rent");
            }

            movie = wkfPurchase.getMovie();
            movie.setStock(movie.getStock() + 1);

            if (new Date().after(wkfPurchase.getEstimatedReturn())) {

                double extraCharge = dayDifference(
                        this.parseLocalDate(wkfPurchase.getEstimatedReturn()),
                        this.parseLocalDate(new Date())
                ) * 1.10;

                wkfPurchase.setTotal(wkfPurchase.getMovie().getRentalPrice().multiply(new BigDecimal(extraCharge)));
            } else {
                wkfPurchase.setTotal(wkfPurchase.getMovie().getRentalPrice());
            }

            movie = ctgMovieRepository.save(movie);
            wkfPurchaseDTO = WkfPurchaseDTO.fromEntity(wkfPurchaseRepository.save(wkfPurchase));

        } catch (Exception ex) {
            throw ex;
        }

        return wkfPurchaseDTO;
    }

    private Pageable getPageable(int page, int size, String sort) {
        Sort sortObj;
        Sort.Direction direction;
        direction = (sort.startsWith("-")) ? Sort.Direction.DESC : Sort.Direction.ASC;

        sortObj = Sort.by(new Sort.Order(direction, sort.replace("-", "")));

        return PageRequest.of(page, size, sortObj);
    }

    // TODO: pass these to Utils.js

    private int dayDifference(LocalDate start, LocalDate end) {
        return ((int) ChronoUnit.DAYS.between(start, end));
    }

    private LocalDate parseLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
