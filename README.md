## Overview

An example project to demonstrate:

* how to create a Spring Boot REST API (see [article](https://tomgregory.com/building-a-spring-boot-application-in-jenkins/))
* how to run Spring Boot in Docker and publish to Docker Hub (see [article](https://tomgregory.com/building-a-spring-boot-application-in-docker-and-jenkins/))
* how to deploy the Spring Boot application to AWS with CloudFormation (see [article](https://tomgregory.com/deploying-a-spring-boot-application-into-aws-with-jenkins/))

### Testing

`./gradlew test`

### Building (no tests)

`./gradlew assemble`

### Building (with tests)

`./gradlew build`

### Running in Docker (Docker installation expected)

`./gradlew assemble docker dockerRun`

### Stopping Docker container

`./gradlew dockerStop`

### Deploying to AWS

`./gradlew awsCfnMigrateStack awsCfnWaitStackComplete -PsubnetId=<your-subnet-id> -Pregion=<your-region>`

### Deleting AWS deployment

`./gradlew awsCfnDeleteStack awsCfnWaitStackComplete`