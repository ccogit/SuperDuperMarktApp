FROM openjdk:17-alpine
EXPOSE 6002
ADD target/Weinservice-0.0.1-SNAPSHOT.jar Weinservice.jar
ENTRYPOINT ["java", "-jar", "/Weinservice.jar"]