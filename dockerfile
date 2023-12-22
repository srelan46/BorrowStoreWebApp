FROM openjdk:17
ADD target/webapp-0.0.1-SNAPSHOT.jar webapp-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "webapp-0.0.1-SNAPSHOT.jar"]