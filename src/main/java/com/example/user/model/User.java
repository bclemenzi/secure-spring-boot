package com.example.user.model;

/**
 * Our stored user record
 * 
 * @author brendanclemenzi
 */
public class User
{
    private String id;
    private String username;
    private String password;
    private String passwordSalt;
    
    private String[] roles;

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    
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
    
    public String[] getRoles()
    {
        return roles;
    }
    public void setRoles(String[] roles)
    {
        this.roles = roles;
    }
}
