# Use an OpenJDK image as the base
FROM openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/admin-service-1.2-SNAPSHOT.jar /app/admin-service.jar

# Expose the port your application runs on
EXPOSE 8080

# Set environment variables for connecting to the Config Server and Eureka
ENV SPRING_PROFILES_ACTIVE docker
ENV CONFIG_SERVER_URI http://config-service:8088
ENV EUREKA_SERVER_URL http://discovery-service:8761/eureka/

# Command to run the application
CMD ["java", "-jar", "admin-service.jar"]
