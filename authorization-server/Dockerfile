FROM openjdk:17-jdk-slim
ARG JAR_FILE=authorization-server/target/*.jar
COPY ${JAR_FILE} authorization-server.jar
RUN bash -c 'touch /authorization-server.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/authorization-server.jar"]