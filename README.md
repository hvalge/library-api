# Library API

An API for a library that gives an overview of which books are available, how many copies are available, 
which books are currently checked out and by whom, and which books are overdue.

## Getting started

### Tech stack

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Gradle](https://gradle.org/install/)
    - Optional to install, as a Gradle wrapper is included in the project.
- [PostgreSQL](https://www.postgresql.org/download/)

## Prerequisites

### PostgreSQL

1. Create a PostgreSQL database in whatever environment and with whatever configuration suits you.
2. Set the following environment variables for the application to connect to the database:
  * SPRING_DATASOURCE_URL - The URL of the PostgreSQL database.
  * SPRING_DATASOURCE_USERNAME - The username of the PostgreSQL database.
  * SPRING_DATASOURCE_PASSWORD - The password of the PostgreSQL database.

The application will automatically seed the database with some test data on each run.

### JWT Signing Key

The application needs a secret JWT Signing Key for authentication. This must be set in the environment variable JWT_SIGNING_KEY.

## Using the application

### Building

```
./gradlew build
```

### Running the application

```
./gradlew run
```

### Run all tests

```
./gradlew test
```
