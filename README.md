[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=ibrahimelhadeg_mars-rover-api)](https://sonarcloud.io/summary/new_code?id=ibrahimelhadeg_mars-rover-api)

# Building & running the project

### Running the tests

`./mvnw test`

### Building the project

Use the command `./mvnw clean install` to build the project

This will also install the resulted artifact on your local repository

### Running Sonar

Ask the project maintainer for a SONAR token to put in the environment variable SONAR_TOKEN,
the run the command `./mvnw verify sonar:sonar`

### Running the app

`./mvnw clean compile exec:java`

# Programming Language

- [Java 17](https://openjdk.org/projects/jdk/17)

# Dependencies

### Utils

- [Lombok](https://projectlombok.org/)
- [Apache Commons](https://commons.apache.org/proper/commons-lang)

### Testing

- [AssertJ](https://joel-costigliola.github.io/assertj)
- [JUnit 5](https://junit.org/junit5)
- [Mockito](https://site.mockito.org/)

### Tools

- [Maven](https://maven.apache.org/wrapper)
- [SonarQube](https://docs.sonarqube.org/)
- [JaCoCo](https://www.eclemma.org/jacoco/)
- [Exec Maven Plugin](https://www.mojohaus.org/exec-maven-plugin)