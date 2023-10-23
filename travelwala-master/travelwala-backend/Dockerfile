FROM openjdk:11-jdk-slim
COPY *.jar /travelwala.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "./travelwala.jar"]
EXPOSE 8080
