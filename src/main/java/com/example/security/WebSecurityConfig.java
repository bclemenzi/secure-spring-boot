package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * @author brendanclemenzi
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        System.out.println("WebSecurityConfig.configure HttpSecurity");
        
        //.csrf().disable()
        
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/").permitAll() // Allow anyone to visit ROOT
                .antMatchers(HttpMethod.POST, "/login") // Only allow a POST for authentication requests to the login service
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                // Filter the api/login requests to direct into the authentication process
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                // ALL other requests are filtered to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        System.out.println("WebSecurityConfig.configure AuthenticationManagerBuilder");
        
        // SAMPLE OF AN IN MEMORY AUTHENTICATION METHOD
        // Create a default account for us to validate against.  We are using our saved 
        //auth.inMemoryAuthentication().withUser("kimk").password("f64a04c1f4780013d23044c141795533").roles("ADMIN");
        
        // SAMPLE OF A DATABASE USER_DETAILS AUTHENTICATION METHOD
        // Get our user details
        auth.userDetailsService(userDetailsService());
    }
    
    @Bean
    public UserDetailsService userDetailsService() 
    {
      return new ExampleUserDetailsService();
    };
    
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() 
    {
        System.out.println("WebSecurityConfig.passwordEncoder");
        
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}