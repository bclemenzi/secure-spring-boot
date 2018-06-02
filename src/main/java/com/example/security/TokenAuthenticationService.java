package com.example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * 
 * @author brendanclemenzi
 */
class TokenAuthenticationService
{
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    
    // TODO Make the secret and token something completely random for higher security.
    static final String SECRET = "Ir0nManRul3s";
    static final String TOKEN_PREFIX = "RingBearer";
    
    // This is the KEY that will be sent back in the response header to the client 
    // after a successful authentication.  This will need to be included in your 
    // API documentation.
    static final String HEADER_STRING = "Authorization";

    /**
     * 
     * @param res
     * @param username
     */
    static void addAuthentication(HttpServletResponse res, String username)
    {
        System.out.println("TokenAuthenticationService.addAuthentication");
        String JWT = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    /**
     * 
     * @param request
     * @return
     */
    static Authentication getAuthentication(HttpServletRequest request)
    {
        System.out.println("TokenAuthenticationService.getAuthentication");
        String token = request.getHeader(HEADER_STRING);
        
        if (token != null)
        {
            // parse the token.
            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();

            return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
        }
        
        return null;
    }
}