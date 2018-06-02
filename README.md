secure-spring-boot
==============

A simple spring-boot application that uses JTW for securing RESTful api calls after user authentication.  The user authentication is performed with a secure passwordHash and salt scheme.  All in all, the process is a lot simpler than most people think...Spring can be customized in so many ways it all just becomes soup.  So, hopefully this little project I built for you will clear things up and give you a solid base to work from.
 
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
 
 Once started, open your browser to http://localhost:8080, and you'll see a simple unsecure welcome page.  Then, using your favorite RESTClient tool, we can send a login request to http://localhost:8080/login. The request body will need to contain the username and password we want to authenticate.  For this example app, use the following JSON string as your body:
 
{"username":"kimk","password":"pass4kim"}

The response for this transaction will be empty, which is what we want.  The JWT system has actually sent us a key in the response header.  The header key is named "Authorization" and it's value is the authorization key we will need to include in the header of all our api calls to the service.  In this case it's a vary long nasty key, which is a good thing:

"RingBearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraW1rIiwiZXhwIjoxNTI4Nzc1MTg2fQ.76NSc90cKV7km7J7vBFn54Iyh6699hr0aHrOKjgyh3XkIlUegDNupuZKycGLLtqVSfhqGRfK392tfgGtLIyaAg"

We will then make a call to one of our secure services.  Lets make a GET request that includes this "Authorization" in the request header.  Execute the GET call to http://localhost:8080/users, no body needed, just the Authorization key in the request header.  As a response we'll receive the following JSON:

{"users":[{"id":"100","firstname":"Kim", "lastname":"Kelly"},{"id":"200","firstname":"Brendan","lastname":"Clemenzi"}]}

What is important?
---------------
 
Now, what are the important parts of this example?

1. In the WebSecurityConfig.java class we have:
 * Declared the we are not using an encryption-based password encorder.
 * Set and inMemoryAuthentication username and hashed password (this mocks our database stored user account)
2. The JWTLoginFilter is where we do the heavy lifting for authenticating our user with the password hash and password salt.  
* I have commented this class fairly well to help explain things.
* The getUserFromDatabaseByUser name will clearly need to be swapped out, but it's important to see the password hash and salt used to make the "kimk" user work.
3. The TokenAuthenticationService class is used to define what our JWT key will be.  it's important to customize the SECRET and TOKEN_PREFIX to help ensure the security of your keys.  I have just put a few silly values in there for now

That's it in a nutshell.
