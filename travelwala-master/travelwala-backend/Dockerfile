# Stage 1: Build jar
FROM maven:3.6.3-openjdk-11-slim as buildjar

WORKDIR /travelwala-backend

COPY pom.xml .
COPY src/main/resources/application.yml ./src/main/resources/
COPY src/main/java/ ./src/main/java

RUN mvn package -DskipTests

# Stage 2: Copy jar file to running environment
FROM alpine:3.14

RUN apk update \
  && apk upgrade \
  && apk add --update openjdk11 tzdata curl unzip bash \
  && rm -rf /var/cache/apk/*

COPY --from=buildjar /travelwala-backend/target/travelwala*.jar ./travelwala.jar

ENTRYPOINT java -jar travelwala.jar

EXPOSE 8080
