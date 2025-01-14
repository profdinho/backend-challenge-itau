FROM openjdk:17-jdk-slim AS build

LABEL authors="Dinho Zanardi"

LABEL maintainer="Dinho Zanardi <prof.dinho.ti@gmail.com>"

EXPOSE 8080

COPY pom.xml mvnw ./

COPY .mvn .mvn

RUN ./mvnw dependency:resolve

COPY src src

RUN ./mvnw package

FROM openjdk:17-jdk-slim

COPY --from=build target/*.jar challenge.jar

ENTRYPOINT ["java", "-jar", "challenge.jar"]
