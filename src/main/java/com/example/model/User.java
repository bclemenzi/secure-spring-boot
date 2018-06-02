package com.example.model;

/**
 * User registration objects stored in the system.
 * 
 * @author brendanclemenzi
 */
public class User
{
    private String username;
    private String password;
    private String passwordSalt;

    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPasswordSalt()
    {
        return passwordSalt;
    }
    public void setPasswordSalt(String passwordSalt)
    {
        this.passwordSalt = passwordSalt;
    }
}
