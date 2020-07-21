package com.jyami.springbootgettingstartedmaven.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by jyami on 2020/07/21
 */
@Service
public class AccountSecurityService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AccountSecurityRepository accountSecurityRepository;

    public AccountSecurity createAccount(String username, String password) {
        AccountSecurity accountSecurity = new AccountSecurity();
        accountSecurity.setUsername(username);
        accountSecurity.setPassword(passwordEncoder.encode(password));
        return accountSecurityRepository.save(accountSecurity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AccountSecurity> byUsername = accountSecurityRepository.findByUsername(username);
        AccountSecurity accountSecurity = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(accountSecurity.getUsername(), accountSecurity.getPassword(), authroities());
    }

    private Collection<? extends GrantedAuthority> authroities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
