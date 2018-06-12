package com.example.security;

import com.example.user.model.User;
import com.example.security.model.AccountCredentials;
import com.example.user.dao.UserDao;
import com.example.util.SecureUUID;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 
 * @author brendanclemenzi
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter
{
    public JWTLoginFilter(String url, AuthenticationManager authManager)
    {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException
    {
        System.out.println("JWTLoginFilter.attemptAuthentication");
        
        AccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);
        System.out.println("Submitted Username: " + creds.getUsername() + "  and Password: "+ creds.getPassword());
        
        // TODO Connect this to some database call.
        UserDao userDao = new UserDao();
        User userModel = userDao.getUserByUsername(creds.getUsername());
        
        // Salt and hash the password passed in to make sure it is the same as the one stored in our database
        String testHash = SecureUUID.generateSaltedMD5(creds.getPassword(), userModel.getPasswordSalt());
        System.out.println("testHash: " + testHash);
        
        // Pass the testHash as our password going forward.
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(creds.getUsername(), testHash, Collections.emptyList());
        
        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException
    {
        System.out.println("JWTLoginFilter.successfulAuthentication");
        
        TokenAuthenticationService.addAuthentication(res, auth.getName());
    }
}