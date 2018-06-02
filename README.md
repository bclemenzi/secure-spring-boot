secure-spring-boot
==============

A simple spring-boot application that used JWT to secure RESTful api endpoints.
 
Files of Interest
------------------------------
 * ExampleApplication.java is the project's base.
 * The UserController.java contains two RESTful api endpoints that we will be securing behind our JWT wall
 * User.java represents our service's registered users...users approved to access the REST api.
 * The com.example.security package contains all the spring authentication filters to secure our system
 * AccountCredentials.java is a simple class we'll use to transform our login request body into a workable object.
 * SecureUUID is a utility class that contains code to help with the password hashing and salting.
 
Getting Started
------------------------------
Execute the following maven command to start the Spring-Boot server:

 * mvn spring-boot:run
 
 Once started, open your browser to http://localhost:8080
 
Application Flow
------------------------------
 * User Login
 
End user executes a POST transaction against http://localhost:8080/login with the following JSON string in the request body:

{"username":"kimk","password":"pass4kim"}  

The response from the /login endpoint will be blank; however, if the authentication is successful, the response header will include an "Authorization" key.  This key will be used to access our secured RESTful endpoints going forward.
