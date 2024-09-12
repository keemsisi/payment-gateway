package com.core.payment.processor.configs;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return User.withUsername("user")
                    .password("password")
                    .roles("USER")
                    .authorities("CARD_TRANSFER", "WALLET_TRANSFER",
                            "BANK_TRANSFER", "CREATE_BANK", "DELETE_BANK",
                            "UPDATE_WALLET", "CREATE_WALLET", "DELETE_WALLET")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}