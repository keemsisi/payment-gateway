package com.core.payment.processor.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomAuthenticationEntryPoint entryPoint;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/v1/api/**").authenticated()
                .anyRequest().permitAll()
                .and().formLogin()
                .permitAll().and()
                .httpBasic().authenticationEntryPoint(entryPoint);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password")
                .roles("USER")
                .authorities("CARD_TRANSFER", "WALLET_TRANSFER",
                        "BANK_TRANSFER", "CREATE_BANK", "DELETE_BANK",
                        "UPDATE_WALLET", "CREATE_WALLET", "DELETE_WALLET");
    }
}
