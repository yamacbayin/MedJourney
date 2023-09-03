package com.yamacbayin.medjourney.configuration;

import com.yamacbayin.medjourney.database.repository.UserRepository;
import com.yamacbayin.medjourney.util.security.JWTFilter;
import com.yamacbayin.medjourney.util.security.SecurityService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserRepository userRepo;

    private final JWTFilter filter;

    private final SecurityService uds;


    private static final String[] AUTH_WHITELIST = {
            "/auth/**",
            "/swagger-ui/**",
            "v3/api-docs/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/api/public/**",
            "/api/public/authenticate",
            "/actuator/*",
            "/swagger-ui/**",
            "/hospitals/get-all",
            "/doctors/get-all"
    };


    private static final String[] USER_AUTH_WHITELIST = {
            "/patients",
            "/patients/**",
            "/hospitals/get-all",
            "/doctors/get-all",
            "/flights/get-all",
            "/hotels/get-all",
            "/flight-tickets",
            "/flight-tickets/**",
    };

    private static final String[] DOCTOR_AUTH_WHITELIST = {
            "/hospitals",
            "/hospitals/**",
            "/appointments",
            "/appointments/**",
            "/medical-reports",
            "/medical-reports/**",
            "/patients",
            "/patients/**",
            "/medical-reports",
            "/medical-reports/**"
    };

    private static final String[] ADMIN_AUTH_WHITELIST = {
            "/airplanes",
            "/airplanes/**",
            "/appointments",
            "/appointments/**",
            "/doctors",
            "/doctors/**",
            "/flights",
            "/flights/**",
            "/flight-seats",
            "/flight-seats/**",
            "/flight-tickets",
            "/flight-tickets/**",
            "/hospitals",
            "/hospitals/**",
            "/hotels",
            "/hotels/**",
            "/hotel-reservations",
            "/hotel-reservations/**",
            "/hotel-rooms",
            "/hotel-rooms/**",
            "/medical-reports",
            "/medical-reports/**",
            "/patients",
            "/patients/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("security");
        http.headers().frameOptions().disable();
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("*"));
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                    configuration.setAllowedHeaders(List.of("*"));
                    configuration.setExposedHeaders(List.of("Content-Disposition"));
                    return configuration;
                }).and()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers(ADMIN_AUTH_WHITELIST).hasRole("admin")
                .requestMatchers(USER_AUTH_WHITELIST).hasRole("user")
                .requestMatchers(DOCTOR_AUTH_WHITELIST).hasRole("doctor")
                .and()

                .userDetailsService(uds)
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
