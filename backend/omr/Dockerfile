FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} omrcs.jar
ENTRYPOINT ["java", "-jar", "omrcs.jar"]
