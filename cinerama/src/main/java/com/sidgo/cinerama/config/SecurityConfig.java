package com.sidgo.cinerama.config;

import com.sidgo.cinerama.auth.JWTAuthorizationFilter;
import com.sidgo.cinerama.model.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${path.allowed.cors}")
    private String allowedPaths;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().and()
                .csrf().disable()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/dynamic/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/images/{image}").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/movies").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/movies/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/users").permitAll()
                .antMatchers(HttpMethod.GET, "/confirm/{userCode}").permitAll()
                .antMatchers(HttpMethod.GET, "/forgot/{email}").permitAll()
                .antMatchers(HttpMethod.GET, "/recover/{userCode}").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/movies/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/v1/movies").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/v1/movies").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/movies").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/users/{id}/role/{idRole}").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(this.allowedPaths));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

