
5. AWS Deployment

   Dockerfile

   FROM openjdk:17-jdk-slim

   WORKDIR /app

   COPY target/blog-service.jar blog-service.jar

   ENTRYPOINT ["java", "-jar", "blog-service.jar"]
