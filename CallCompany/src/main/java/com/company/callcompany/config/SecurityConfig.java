package com.company.callcompany.config;

import com.company.callcompany.entity.enums.RoleTypes;
import com.company.callcompany.security.JwtFilter;
import com.company.callcompany.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private  AuthService authService;
    private JwtFilter jwtFilter;
@Autowired
    public SecurityConfig(@Lazy AuthService authService, JwtFilter jwtFilter) {
        this.authService = authService;
    this.jwtFilter = jwtFilter;
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
               .httpBasic().disable()
               .authorizeRequests()
               .antMatchers("/path/branch").hasRole(RoleTypes.BRANCH_MANAGER.name())
               .antMatchers("/path/companyCode").hasRole(RoleTypes.MANAGER.name())
               .antMatchers("/path/serviceTypes").hasRole(RoleTypes.SERVICE_MANAGER.name())
               .antMatchers("/api/number").hasRole(RoleTypes.NUMBERS_MANAGER.name())
               .antMatchers("/path/ussd_codes").hasRole(RoleTypes.SERVICE_MANAGER.name())
               .antMatchers("/api/**")
               .permitAll()
               .anyRequest().authenticated();
       http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
       http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }
@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
