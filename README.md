## Redis as HTTP session holder (Spring Boot)

This is a demo project to demonstrate the power of spring boot
http session stored inside redis.

*Benefit* of using redis as a persistence session storage:   
- The application layer will get some headroom as redis will be used to offload sessions
- Server restarts won't affect the session and user won't get logged out
- We can run multiple instance of the server and load balance user request without user getting logged out while routing to different instance

### Dependency
- Java version `17`
- You will need `redis` to be running in it's default host & port (change them to your liking from `application.properties` file if you want)

### How to run and test the load balancing
1. Install `nginx` for your operation system
2. Take a back up of you existing `ngingx.conf` file if you have one
3. Replace the old `ngingx.conf` with the file present in the root directory of this project
4. Now run two separate instance of the application one in port `8080` and other in port `8081`   
   1. Open one command prompt and past this command to run in port `8080`
      ```shell
      mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8080"
      ```
   2. Open another command prompt and paste this command to run in port `8081`
      ```shell
      mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
      ```


> Now visit `http://localhost`

You will need to login. There are two in memory user:   
1. UserName: `user` Password: `password` Authorized Endpoints: `/`
2. UserName: `admin` Password: `password` Authorized Endpoints: `/` & `/admin`   

If you hit and keep refresh page on `/` endpoint. Now and go back and forth between the two opened terminal prompt now   
you will find out that `user hit` will be printed on different terminal prompt each time which will cycle through the two prompt.   

_Which proves the application is getting load balanced without logging out the user_