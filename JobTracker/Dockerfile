# Stage 1: Build WAR using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app
COPY JobTracker /app

RUN mvn clean package

# Stage 2: Deploy WAR into Tomcat
FROM tomcat:9.0-jdk17

COPY --from=builder /app/target/jobtracker.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]