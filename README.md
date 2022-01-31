# cg_code_challenge
CG Code Challenge

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java JDK 11
- MySQL 8.0 

### Installing

To checkout the project from the repository, create a folder where you want your code to be stored in your local machine and go inside it from the command line

```
git clone git@github.com:carla10ar/cg_code_challenge.git
```

Once that the project was downloaded go to the project path and run

```
 ./gradlew clean build
```
This should compile and run all unit and integration tests.

### Installing MySql in docker

To create an instance of MySql 8.0 in docker go to terminal and run:

```
docker run --name mysql-8 -v mysqldbvol:/var/lib/mysql -p 3306:3306 -e MYSQL_USER=user -e MYSQL_PASSWORD=password -e MYSQL_DATABASE=sample -e MYSQL_ROOT_PASSWORD=password -d mysql/mysql-server:8.0
```

And to start it run:

```
docker start mysql-8
```

## Running the application from terminal

#### Starting the app

```
./gradlew bootRun
```

This will run `codechallengee` on `http://localhost:8181`

## Running the application from IntelliJ
Go to `Edit Configurations...`

Click in the plus (`+`) button to add a new configuration.

Select `Spring Boot` application.

When all is set up, press the Run `Application` button

This will run `CodechallengeApplication` on `http://localhost:8181`

## Coding style tests

We are using Google Java Format plugin that formats

To format the code run

```
./gradlew goJF
```

To verify the code run

```
./gradlew verGJF
```


### IntellIj Code style configuration:

* Install google-java-format plugin and enabled it.
* In  Editor->Code Style->Java configure:
    - In "Set from" link select XML
    - In Import schema select the config/checkstyle/google-formatter.xml file.


## APIs

### Create trainer
#### Request
```
curl --location --request POST 'http://localhost:8181/api/v1/trainer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email" : "trainer@campgladiator.com",
    "phone" : "5125125120",
    "first_name": "Fearless",
    "last_name": "Contender"
}'
```
#### Response
```
{
    "id": null,
    "email": "trainer@campgladiator.com",
    "phone": "5125125120",
    "first_name": "Fearless",
    "last_name": "trainer@campgladiator.com"
}
```

### Get trainer by id
#### Request
```
curl --location --request GET 'http://localhost:8181/api/v1/trainer/{trainerId}'
```

#### Response
```
{
    "id": "trainer-id-000001",
    "email": "trainer@campgladiator.com",
    "phone": "5125125120",
    "first_name": "Fearless",
    "last_name": "Contender"
}
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - IoC framework used (current version 2.6.3)
* [Gradle](https://gradle.org/) - Dependency Management

## Next Steps
These would be the next steps if I had more time to work on this challenge:
* Document API (with swagger or similar)
* Add more validations to create new trainer endpoint 
  * Email uniqueness
  * Phone number correctness
* Use test-containers for ITs
* Add more scenarios to UTs and ITs
* Dockerize development environment
* Adding Jacoco reports
* Configure Github + Jenkins + SonarQube
