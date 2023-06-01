package sn.niit.restauranManagementApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import sn.niit.restauranManagementApplication.Security.CustomUserDetailsService;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeHttpRequests((authorize) -> authorize.requestMatchers().permitAll()
                .requestMatchers().permitAll()
                .requestMatchers().hasRole("ADMIN"))
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/processlogin")
                                .successHandler(((request, response, authentication) -> {
                                    List<String> roles = authentication.getAuthorities().stream()
                                            .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

                                    String emp_role = "ROLE_EMPLOYEE";
                                    String user_role = "ROLE_USER";
                                    if (roles.contains(user_role))
                                        System.out.println("The user is a ...user.");
                                    if (roles.contains(emp_role))
                                        System.out.println("The user is an employee.");
                                    System.out.println(String.format("These are the roles: %s", roles.toString()));
                                    response.sendRedirect(roles.contains(emp_role) ? "/admin/dashboard" : "/site/home");
                                }))
                                .permitAll())
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll());
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}
