FROM container-registry.oracle.com/java/jdk:latest

WORKDIR /app

COPY build/libs/*.jar pet_store-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "pet_store-0.0.1-SNAPSHOT.jar"]
