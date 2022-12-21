package com.library.config;


import com.library.config.handler.ApiAccessDeniedHandler;
import com.library.config.handler.ApiEntryPoint;
import com.library.config.jwt.JwtAuthenticationFilter;
import com.library.config.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@PropertySource("classpath:/application.properties")
public class SecurityConfig {

    private final DataSource dataSource;

    private final String jwtKey;

    public SecurityConfig(DataSource dataSource, @Value("${jwt.signing.key}") String jwtKey) {
        this.dataSource = dataSource;
        this.jwtKey = jwtKey;
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.exceptionHandling()
                .authenticationEntryPoint(new ApiEntryPoint());

        http.exceptionHandling()
                .accessDeniedHandler(new ApiAccessDeniedHandler());

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers("/api/status").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/api/**").authenticated();

        http.apply(new FilterConfigurer());

        return http.build();
    }

    public class FilterConfigurer extends AbstractHttpConfigurer<FilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager manager = http.getSharedObject(AuthenticationManager.class);

            var loginFilter = new JwtAuthenticationFilter(
                    manager, "/api/login", jwtKey);

            http.addFilterBefore(loginFilter,
                    UsernamePasswordAuthenticationFilter.class);

            var authorizationFilter = new JwtAuthorizationFilter(jwtKey);

            http.addFilterBefore(authorizationFilter, AuthorizationFilter.class);
        }
    }

    @Bean
    public UserDetailsService userDetailService() {
        var manager = new JdbcUserDetailsManager(dataSource);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("STAFF"));
        PasswordEncoder passwordEncoder = passwordEncoder();
        String encodedPassword = passwordEncoder.encode("staff");
        manager.createUser(new User("staff", encodedPassword, authorities));

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(true);
    }
}