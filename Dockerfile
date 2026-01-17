# Build stage
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy Gradle wrapper + config
COPY gradlew .
COPY gradlew.bat .
COPY gradle gradle
COPY settings.gradle .
COPY gradle.properties .

# Copy app module (where src exists)
COPY app app

# Build bootJar from the app module
RUN ./gradlew :app:bootJar --no-daemon -x test

# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the jar from app/build/libs
COPY --from=build /app/app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

