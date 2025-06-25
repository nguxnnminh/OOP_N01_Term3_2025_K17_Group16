FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/OOP_NO1_Term3_2025_K17_Group16-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
