package com.example.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple REST controller with two transaction endpoints.  Both 
 * of which are locked behind a JWT authentication wall
 * 
 * @author brendanclemenzi
 */
@RestController
public class UserController
{
    @RequestMapping("/users")
    public @ResponseBody String getUsers()
    {
        // TODO Get our users from the database
        String jsonReesponse = "{\"users\":[{\"id\":\"100\",\"firstname\":\"Kim\", \"lastname\":\"Kelly\"},{\"id\":\"200\",\"firstname\":\"Brendan\",\"lastname\":\"Clemenzi\"}]}";
        
        return jsonReesponse;
    }
    
    @RequestMapping("/user/{userId}")
    public @ResponseBody String getUsers(@PathVariable String userId)
    {
        // TODO Get our users from the database
        String jsonReesponse = "{\"id\":\"" + userId + "\",\"firstname\":\"Kim\",\"lastname\":\"Kelly\"}";
        
        return jsonReesponse;
    }
}