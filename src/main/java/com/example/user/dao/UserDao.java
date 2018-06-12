package com.example.user.dao;

import com.example.user.model.User;

/**
 * The UserDao class should talk to your database where registered user records are stored.
 * 
 * @author brendanclemenzi
 */
public class UserDao
{
    public UserDao()
    {
    }
    
    public User getUserByUsername(String username)
    {
        // THIS IS JUST A DUMBY USER RECORD TO MOCK WHAT COULD BE RETURNED FROM THE DATABASE
        User tmpUser = new User();
        tmpUser.setId("12345");
        tmpUser.setUsername("kimk");
        tmpUser.setPassword("f64a04c1f4780013d23044c141795533");
        tmpUser.setPasswordSalt("453gt7sbe");
        
        String[] userRoles = {"ADMIN"};
        tmpUser.setRoles(userRoles);

        return tmpUser;
    }
}
