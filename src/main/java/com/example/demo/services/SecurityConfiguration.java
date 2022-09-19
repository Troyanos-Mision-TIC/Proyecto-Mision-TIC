package com.example.demo.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void  configure(AuthenticationManagerBuilder auth) throws Exception{
        /*super.configure(auth);*/
        auth.inMemoryAuthentication()
                .withUser("asd")
                .password("asd")
                .roles("SUP")
                .and()
                .withUser("otro")
                .password("password")
                .roles("ADMIN");
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*super.configure(http);*/
        http.authorizeRequests()
                .antMatchers("/empresas").hasRole("ADMIN")
                .antMatchers("/").hasAnyRole("SUP", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

}
