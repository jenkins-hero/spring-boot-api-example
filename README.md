## Overview

An example project to demonstrate:

* how to create a Spring Boot REST API
* how to test a Spring Boot REST API
* how to run Spring Boot in Docker

### Testing

`./gradlew test`

### Building (no tests)

`./gradlew assemble`

### Building (with tests)

`./gradlew build`

### Running in Docker (Docker installation expected)

`./gradlew assemble docker dockerRun`

### Stoppping Docker container

`./gradlew dockerStop`