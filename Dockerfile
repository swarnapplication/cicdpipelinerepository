FROM maven:3.9.4 AS build
WORKDIR /app
ARG CONTAINER_PORT
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app/
RUN mvn clean
RUN mvn package -DskipTests -X

FROM openjdk:8-jdk-alpine
COPY --from=build /app/target/*.jar app.jar
EXPOSE ${CONTAINER_PORT}
CMD [ "java","-jar","/app.jar"]


#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ADD target/SpringBootDBAppTest-0.0.1-SNAPSHOT.jar SpringBootDBAppTest.jar
#EXPOSE 8585
#ENTRYPOINT [ "java","-jar","/SpringBootDBAppTest.jar"]