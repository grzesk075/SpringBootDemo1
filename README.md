# SpringBootDemo1
Spring Boot sandbox

To build `mvn clean install`, or even better by Maven Wrapper `./mvnw clean install`.

To run just start on IntelliJ Idea main class with `@SpringBootApplication` (`DemoApplication`),
or type `./mvnw spring-boot:run` or execute `java -jar demo-0.0.1-SNAPSHOT.jar` (executable jar with libraries like embeded Tomcat).

Prerequisites:
* run mysql on docker using scripts from `./mysql` folder.

Main page: http://localhost:8080/index0.html.

Top level RESTful services are listed automatically: http://localhost:8080.
