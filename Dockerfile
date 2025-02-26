FROM openjdk:11

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/ScientificCalculator-1.0-SNAPSHOT.jar calculator.jar

# Run the application
CMD ["java", "-jar", "calculator.jar"]
