package com.example.security;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.user.dao.UserDao;
import com.example.user.model.User;

public class ExampleUserDetailsService implements UserDetailsService
{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        // TODO Load your user from a database call
        UserDao userDao = new UserDao();
        User userModel = userDao.getUserByUsername(username);
        
        UserBuilder builder = null;
        
        if(userModel != null) 
        {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(userModel.getPassword());
            builder.roles(userModel.getRoles());
        } 
        else 
        {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
}
