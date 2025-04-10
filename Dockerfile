FROM eclipse-temurin:21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} main_service.jar
ENTRYPOINT ["java", "-jar", "/main_service.jar"]
