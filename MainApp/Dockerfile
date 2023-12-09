FROM openjdk:17-alpine
EXPOSE 6001
ADD target/MainApp-0.0.1-SNAPSHOT.jar MainApp.jar
ENTRYPOINT ["java", "-jar", "/MainApp.jar"]