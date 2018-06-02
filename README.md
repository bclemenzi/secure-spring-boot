secure-spring-boot
==============

A simple spring-boot application that uses JWT to secure RESTful api endpoints.  User authentication is performed with a secure passwordHash and salt scheme.
 
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
 
User Login Flow
---------------
 
End user executes a POST transaction against http://localhost:8080/login with the following JSON string in the request body:

{"username":"kimk","password":"pass4kim"}  

The response from the /login endpoint will be blank; however, if the authentication is successful, the response header will include an "Authorization" key.  This key will be used to access our secured RESTful endpoints going forward.

Secure Service Call
---------------
Using the "Authorization" key from our login header, we can now execute a GET request to query one of our secured RESTful edpoints.  
 * Add Authorization key and value to our request header.
 * Do a GET request against http://localhost:8080/users
 
If all goes well, you'll have the following JSON string returned to you:

{"users":[{"id":"100","firstname":"Kim", "lastname":"Kelly"},{"id":"200","firstname":"Brendan","lastname":"Clemenzi"}]}

