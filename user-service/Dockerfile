FROM openjdk:17-jdk-slim
ARG JAR_FILE=user-service/target/*.jar
COPY ${JAR_FILE} user-service.jar
RUN bash -c 'touch /user-service.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/user-service.jar"]