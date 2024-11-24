FROM eclipse-temurin:17-jdk AS builder
COPY . /app/
WORKDIR /app/
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts
COPY . /app/.
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/vaadin-0.0.1-SNAPSHOT.jar vaadin-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "vaadin-0.0.1-SNAPSHOT.jar"]

