
package com.example.demo2.api.v1.local.proyecto1.auth;

import com.example.demo2.api.v1.local.proyecto1.auth.jwt.JwtTokenFilter;
import com.example.demo2.api.v1.local.proyecto1.auth.jwt.JwtEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )//Indicar solo para el Admin...
public class AuthSecurity extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserRolesPrincipalService authUserRolesService;
    @Autowired
    JwtEntryPoint jwtEntryPoint;
    
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserRolesService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and().csrf().disable() // Inhabilitar cookies
            .authorizeRequests()
            .antMatchers("/auth/**").permitAll() // Permitido a TODO mundo (login)
            .anyRequest().authenticated() // Para el resto debe estar autenticado
            .and() // Verificar si viene Token
            .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
            .and() // Politica de Session SIN estado:
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        // Por cada Peticion, comprueba el Token y pasa el User al Contexto de Autenticacion
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
}
