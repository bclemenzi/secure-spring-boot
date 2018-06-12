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
public class ViewUserController
{
    @RequestMapping("/user/{userId}")
    public @ResponseBody String getUsers(@PathVariable String userId)
    {
        // TODO Get our users from the database
        String jsonReesponse = "{\"id\":\"" + userId + "\",\"firstname\":\"Kim\",\"lastname\":\"Kelly\"}";
        
        return jsonReesponse;
    }
}