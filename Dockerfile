FROM openjdk:17-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM openjdk:17-jdk-alpine
COPY --from=builder /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]