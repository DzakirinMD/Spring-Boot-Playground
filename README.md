# Spring-Boot-Playground

Placeholder for simple Spring-Boot Application with no UI.

Create the project with Spring Initializr: [link](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.7.0&packaging=jar&jvmVersion=17&groupId=com&artifactId=spring-boot-playground&name=spring-boot-playground&description=Demo%20project%20for%20Spring%20Boot&packageName=com.spring-boot-playground&dependencies=web,data-jpa,postgresql)

Dependencies used during Spring Intializr:
1. Spring Web
2. Spring Data JPA
3. PostgreSQL Driver

Development Environment :
* Java Version: 11
* Docker: postgres:latest image (database in docker)
* Database: PostgreSQL
* Postman (for API Testing)
* Properties file : application.yml


<h3>Docker:</h3>

To enter docker: ```docker exec -it yourdockername psql -U postgres``` </br>
(enter into postgress container, as user postgres, iniate psql)
Run the DDL script in the database

Database:
</br>DDL: spring-boot-playground\src\main\resources\DDL\DDL.sql

Package and run application:
1. mvn-clean
2. mvn-install
3. goto /spring-boot-playground/target/
4. take spring-boot-playground-0.0.1-SNAPSHOT.jar and put the binary somewhere you want
5. open command prompt and run command below
6. ```java -jar spring-boot-playground-0.0.1-SNAPSHOT.jar```
   1. Specify port: ```java -jar spring-boot-playground-0.0.1-SNAPSHOT.jar --server.port=8052```
   2. press ```ctrl + c``` to stop the application in server