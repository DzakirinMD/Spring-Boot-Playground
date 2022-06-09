# Spring-Boot-Playground

Placeholder for simple Spring-Boot Application with no UI.

Create the project with Spring Initializr: [link](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.7.0&packaging=jar&jvmVersion=17&groupId=com&artifactId=spring-boot-playground&name=spring-boot-playground&description=Demo%20project%20for%20Spring%20Boot&packageName=com.spring-boot-playground&dependencies=web,data-jpa,postgresql)

Dependencies:
1. Spring Web
2. Spring Data JPA
3. PostgreSQL Driver

Development Environment :
* Java Version: 17 
* Docker (database in docker)
* Database: PostgreSQL
* Postman (for API Testing)


<h3>How to Run the Application</h3>
1.


Service Layer: Responsible for Business Logic

Docker:

To enter docker: ```docker exec -it postgres-E4Sh psql -U postgres``` </br>
(enter into postgress container, as user postgres, iniate psql)

Database:
</br>DDL: spring-boot-playground\src\main\resources\DDL\DDL.sql
</br>DML: spring-boot-playground\src\main\resources\DML\DML.sql

REMEMBER TO CHANGE THE PORT in the application.properties !!