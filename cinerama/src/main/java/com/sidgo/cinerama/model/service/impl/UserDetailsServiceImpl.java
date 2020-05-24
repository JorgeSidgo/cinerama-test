package com.sidgo.cinerama.model.service.impl;

import com.sidgo.cinerama.model.dto.SctUserDTO;
import com.sidgo.cinerama.model.repository.SctUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SctUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SctUserDTO userDTO = userRepository.authUser(username);

        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(userDTO.getProfile());

        return new User(userDTO.getUserName(), userDTO.getPwd(), grantedAuthorities);
    }
}
