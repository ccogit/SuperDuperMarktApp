FROM openjdk:17-alpine
EXPOSE 6003
ADD target/KaeseService-0.0.1-SNAPSHOT.jar KaeseService.jar
ENTRYPOINT ["java", "-jar", "/KaeseService.jar"]