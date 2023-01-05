# Spring Boot Test Container Example code

This repository is about using Test container on Spring Boot. There are separated example of using Test container, first of all using PostgreSQL container.

Later, i will add using kafka and mockserver container

## Features

- BookController and BookControllerTests class
- CommentController class

## How to run
`git clone https://github.com/farhanatsani/spring-test-container-example`

- For manually test, you must connect to postgresql either using docker or installing on your machine
- Run with following command :
<br /> <br />
`mvn clean install`
<br />
`mvn spring-boot:run`
<br /> <br />

- For automation test, you're not required to connect postgresql
- Just run with following command :
<br /> <br />
`mvn test -Dtest=BookControllerTests`

