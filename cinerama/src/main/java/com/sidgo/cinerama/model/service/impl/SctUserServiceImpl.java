package com.sidgo.cinerama.model.service.impl;

import com.sidgo.cinerama.model.dto.MailDTO;
import com.sidgo.cinerama.model.dto.PasswordUpdateDTO;
import com.sidgo.cinerama.model.dto.SctUserDTO;
import com.sidgo.cinerama.model.entity.SctProfile;
import com.sidgo.cinerama.model.entity.SctUser;
import com.sidgo.cinerama.model.exception.ExistingUserException;
import com.sidgo.cinerama.model.repository.SctUserRepository;
import com.sidgo.cinerama.model.service.MailService;
import com.sidgo.cinerama.model.service.SctUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SctUserServiceImpl implements SctUserService {

    @Autowired
    SctUserRepository sctUserRepository;

    @Autowired
    MailService mailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${web.app.url}")
    private String webAppUrl;

    @Value("${mail.linkPrefix.confirm}")
    public String CONFIRM_LINK_PREFIX;

    @Value("${mail.linkPrefix.recover}")
    public String RECOVER_LINK_PREFIX;

    @Override
    public Page<SctUserDTO> getUsers(Specification<SctUser> spec, int page, int size, String sort) {
        Page<SctUser> users;
        Pageable pageable;

        pageable = getPageable(page, size, sort);
        users = sctUserRepository.findAll(spec, pageable);
        return users.map(SctUserDTO::fromEntity);
    }

    @Override
    public SctUserDTO getUser(long id) {
        return sctUserRepository.getUserByIdShow(id);
    }

    @Override
    public SctUserDTO save(SctUserDTO userDTO) throws IOException, MessagingException, ExistingUserException {
        SctUser sctUser = new SctUser();
        SctUserDTO sctUserDTO = new SctUserDTO();
        try {

            if (sctUserRepository.getUserByUsername(userDTO.getUserName()) != null)
                throw new ExistingUserException("username");
            if (sctUserRepository.getUserByEmail(userDTO.getEmail()) != null)
                throw new ExistingUserException("e-mail");

            String userCode = UUID.randomUUID().toString();

            sctUser.setCode(userCode);
            sctUser.setFirstNames(userDTO.getFirstNames());
            sctUser.setLastNames(userDTO.getLastNames());
            sctUser.setUserName(userDTO.getUserName());
            sctUser.setEmail(userDTO.getEmail());
            sctUser.setProfile(new SctProfile(1));

            /*
             *   The field State from Entity SctUser varies depending on the following:
             *       1 -> Active User
             *       2 -> Unconfirmed User
             *       3 -> User waiting for password reset
             *       4 -> Inactive User (soft delete)
             */
            sctUser.setState(2);
            /*
             */

            sctUser.setPwd(passwordEncoder.encode(userDTO.getPwd()));
            sctUser.setCreatedAt(new Date());

            sctUser = sctUserRepository.save(sctUser);

            sctUserDTO = SctUserDTO.fromEntity(sctUser);

            MailDTO mailDTO = new MailDTO();

            Map<String, Object> model = new HashMap();
            model.put("name", sctUserDTO.getFirstNames() + " " + sctUserDTO.getLastNames());
            model.put("username", sctUserDTO.getUserName());
            model.put("confirmationLink", this.CONFIRM_LINK_PREFIX + userCode);
            mailDTO.setTo(sctUserDTO.getEmail());
            mailDTO.setModel(model);

            mailService.sendMail(mailDTO, "newUserMail.html", MailDTO.NEW_USER);

        } catch (ExistingUserException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return sctUserDTO;
    }

    @Override
    public SctUserDTO changeRole(long idUser, long idRole) {
        SctUser user = sctUserRepository.getOne(idUser);

        user.setProfile(new SctProfile(idRole));

        return SctUserDTO.fromEntity(sctUserRepository.save(user));
    }


    @Override
    public String confirmAccount(String userCode) {
        Context context = new Context();
        SctUser user = sctUserRepository.getUserEntityByCode(userCode);
        user.setState(1);
        user = sctUserRepository.save(user);
        context.setVariable("link", this.webAppUrl);
        context.setVariable("message", "Confirmed account");
        String html = templateEngine.process("redirect.html", context);
        return html;
    }

    @Override
    public String recoverPassword(String userCode, PasswordUpdateDTO passwordUpdateDTO) {
        Context context = new Context();
        SctUser user = sctUserRepository.getUserEntityByCode(userCode);
        user.setPwd(passwordEncoder.encode(passwordUpdateDTO.getNewPassword()));
        user = sctUserRepository.save(user);
        context.setVariable("link", this.webAppUrl);
        context.setVariable("message", "Password updated");
        String html = templateEngine.process("redirect.html", context);
        return html;
    }

    @Override
    public boolean forgotPassword(String email) throws Exception {

        try {
            SctUser user = sctUserRepository.getUserEntityByEmail(email);

            if (user == null)
                throw new Exception("No users found with this e-mail");

            SctUserDTO sctUserDTO;
            user.setState(3);

            sctUserDTO = SctUserDTO.fromEntity(sctUserRepository.save(user));

            MailDTO mailDTO = new MailDTO();

            Map<String, Object> model = new HashMap();
            model.put("name", sctUserDTO.getFirstNames() + " " + sctUserDTO.getLastNames());
            model.put("confirmationLink", this.RECOVER_LINK_PREFIX + user.getCode());
            mailDTO.setTo(sctUserDTO.getEmail());
            mailDTO.setModel(model);

            mailService.sendMail(mailDTO, "forgotPassword.html", MailDTO.FORGOT_PASSWORD);

            return true;
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public SctUserDTO updatePassword(SctUserDTO userDTO) {
        return null;
    }

    private Pageable getPageable(int page, int size, String sort) {

        if (sort.replace("-", "").equals("pwd"))
            return PageRequest.of(size, page, null);

        Sort sortObj;
        Sort.Direction direction;
        direction = (sort.startsWith("-")) ? Sort.Direction.DESC : Sort.Direction.ASC;

        sortObj = Sort.by(new Sort.Order(direction, sort.replace("-", "")));

        return PageRequest.of(page, size, sortObj);
    }
}
