FROM openjdk:17-oracle
ADD build/libs/*.jar parking-app.jar
ENTRYPOINT ["java", "-jar", "parking-app.jar"]
EXPOSE 8080