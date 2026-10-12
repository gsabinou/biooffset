package br.com.fiap.biooffset.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaSeguranca(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer.disable())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/projetos").hasAnyRole("DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/empresas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/projetos").hasAnyRole("DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/empresas").hasRole( "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/projetos/").hasAnyRole("DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/empresas/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/comprar").hasAnyRole("COMPRADOR", "DESENVOLVEDOR")
                        .requestMatchers(HttpMethod.POST, "/api/creditos-carbono").hasAnyRole("DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/empresas/projetos/").hasAnyRole("COMPRADOR", "DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/empresas").hasAnyRole("COMPRADOR", "DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/empresas/transacoes/").hasAnyRole("COMPRADOR", "DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/creditos-carbono/").hasAnyRole("COMPRADOR", "DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/creditos-carbono").hasAnyRole("COMPRADOR", "DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/projetos").hasAnyRole("COMPRADOR", "DESENVOLVEDOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/projetos/creditos/").hasAnyRole("COMPRADOR", "DESENVOLVEDOR", "ADMIN")
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(verificarToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager (
            AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
