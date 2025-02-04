package com.example.webregister.config;

import com.example.webregister.service.user.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    /* ログイン・ログアウトの認証設定 */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(login -> login
                        .loginPage("/toLogin")
                        .defaultSuccessUrl("/main", true)
                        .permitAll()
                )

                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/favicon.ico", "/error")
                        .permitAll()
                        .requestMatchers("/main/**")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/toLogin")
                        .permitAll()
                );
        return http.build();
    }

    /* DBとの連携による認証 */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

}
