package com.soa.bakend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {

    @Bean

    public InMemoryUserDetailsManager userDetailsManager() {

        /*UserDetails gc = User.builder()
                .username("mario")
                .password("{noop}mario123")
                .roles("Empleado")
                .build();*/

        UserDetails gc = User.builder()
                .username("leo")
                .password("{noop}leo123")
                .roles("Empleado", "Jefe")
                .build();

        /*UserDetails luci = User.builder()
                .username("luci")
                .password("{noop}luci123")
                .roles("Empleado", "Jefe")
                .build();*/
        //para encriptado bcryptcalculator.com/encode
        return new InMemoryUserDetailsManager(gc);

    }

    @Bean
    @Autowired

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configure -> {
            configure
                    .requestMatchers("/v1/**").permitAll()
                    .requestMatchers("/v2/**").permitAll()
                    .requestMatchers("/v3/**").permitAll()
                    .requestMatchers("/v4/**").permitAll();

        });
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();

    }

}
