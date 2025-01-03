package com.Bioaqua.global.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Bioaqua.global.entity.Users;
import com.Bioaqua.global.repository.UsersRepo;

import java.util.Collections;

@Service
public class UserDetailsServiceApp implements UserDetailsService {

    private final UsersRepo appUserRepo;

    public UserDetailsServiceApp(UsersRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = appUserRepo.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found !!");
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }
}
