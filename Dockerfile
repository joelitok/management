FROM maven:3.8.4-openjdk-17 AS build
# copy only pom first to utilize docker cache
COPY pom.xml /usr/src/app/pom.xml
# cache dependencies
RUN mvn -f /usr/src/app/pom.xml dependency:go-offline
COPY src /usr/src/app/src
# overwrite properties with docker specific properties
RUN mv /usr/src/app/src/main/resources/application-docker.properties /usr/src/app/src/main/resources/application.properties
RUN mvn -f /usr/src/app/pom.xml clean package
RUN mv /usr/src/app/target/*.jar /usr/src/app/target/app.jar

FROM openjdk:17.0.2-jdk
COPY --from=build /usr/src/app/target/app.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]