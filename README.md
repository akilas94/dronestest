# Drones Test

This project acts as a medication delivery system.

## Table of contents

- [Requirement](#requirements)
- [Getting Started](#getting-started)
    - [Running locally](#running-locally)
- [API Documentation](#api-documentation)
    - [Swagger ui](#swagger-ui)

## Requirements

- Java 11
- Maven 3
- Mysql or h2
- 
  Also you can use [database](http://localhost:8080/h2-console/)


#### Running locally

- ##### Build an executable JAR
  You can run the application from the command line using:

    ```
    mvn clean install
    cd ./drones-test
    mvn spring-boot:run
   ```

  Or you can build a single executable JAR file that contains all the necessary     dependencies, classes, and resources with:

    ```
    mvn clean package
    ```
  Then you can run the JAR file with:
    ```
    java -jar target/*.jar
    ```
  Instead of mvn you can also use the gradle-wrapper /gradle to ensure you have     everything necessary to run the Gradle build.



#### Swagger ui

Also you can use [swagger ui](http://localhost:8080/swagger-ui.html) as a api documentation