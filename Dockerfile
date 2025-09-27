
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .

RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests package


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app


RUN addgroup -S spring && adduser -S spring -G spring

# ia jar-ul rezultat (oricum s-ar numi) și numește-l app.jar
COPY --from=build /app/target/*.jar /app/app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=25.0"
EXPOSE 8080
USER spring
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
