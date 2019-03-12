# A platform to publish scientific work.
Backend: Spring Boot
Frontend: Vue js

## Get started:
Clone the repository.
To run Spring Boot backend, open /iti0208 directory in IntelliJ and run it (runs on port 8090).
To run Vue frontend, open /app directory and use command 'npm run serve'.

## Security:
Right now it is possible to see all posts without logging in, but for posting anything, you need to be logged in.
After logging in, frontend receives a JWT token in response 'Headers' -> 'Authorization'. The content of this header starts like this: 'Bearer ...'. First we need to save the JWT to browsers local storage. Every time a user tries to post anything, the 'Authorization' header with content needs to be added to the request. 
Logging out should probably be handled in browser by deleting the JWT.

For testing there are two users: 
1) username: user_test, password: user_test, authorities: ROLE_USER
2) username: admin_test, password: admin_test, authorities: ROLE_ADMIN