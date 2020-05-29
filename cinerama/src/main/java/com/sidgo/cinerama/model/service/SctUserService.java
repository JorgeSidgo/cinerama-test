package com.sidgo.cinerama.model.service;

import com.sidgo.cinerama.model.dto.PasswordUpdateDTO;
import com.sidgo.cinerama.model.dto.SctUserDTO;
import com.sidgo.cinerama.model.entity.SctUser;
import com.sidgo.cinerama.model.exception.ExistingUserException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SctUserService {
    Page<SctUserDTO> getUsers(Specification<SctUser> spec, int page, int size, String sort);

    SctUserDTO getUser(long id);

    SctUserDTO save(SctUserDTO userDTO) throws IOException, MessagingException, ExistingUserException;

    SctUserDTO changeRole(long idUser, long idRole);

    String confirmAccount(String userCode);

    String recoverPassword(String userCode, PasswordUpdateDTO passwordUpdateDTO);

    boolean forgotPassword(String email) throws Exception;

    SctUserDTO updatePassword(SctUserDTO userDTO);

}
