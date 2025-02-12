package com.platzi.pizzeria_presto.web.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // también se puede deshabilitar con: .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // nuestro proyecto no tendrá estado, no va a almacenar sesiones, cada vez que reciba una petición se va a validar como si fuera una petición completamente nueva
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/pizzas/*").hasAnyRole("ADMIN")
                        // Solo ADMIN puede usar POST en pizzas
                        .requestMatchers(HttpMethod.POST, "/api/pizzas/*").hasRole("ADMIN")
                        // Solo ADMIN puede usar PUT en todos los métodos
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers("/api/pedidos/*").hasRole("ADMIN")
                        .requestMatchers("/api/cliente/*").denyAll()
                        .requestMatchers("/api/detalles_pedidos/**").permitAll()
                        // Cualquier otra ruta necesita autenticación
                        .anyRequest()
                        .authenticated() // Proteger todas las demás rutas
                )
                //.httpBasic(Customizer.withDefaults()); // Habilita autenticación básica
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // este usernamePasswordAuthenticationFilter es el primer filtro de seguridad de autenticación ue tiene Spring
        return http.build();
    }
    /*
    @Bean
    public UserDetailsService memoryUsers(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles("CUSTOMER")
                .build();
        return new InMemoryUserDetailsManager(admin, customer);
    }
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); // para que utilice el AuthenticationManager por defecto.
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
