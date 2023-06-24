# Dockerfile

# https://www.nginx.com/
# https://hub.docker.com/_/nginx


FROM openjdk:17
LABEL maintainer="My Todo List Project for Spring Boot"
VOLUME /tmp
ARG JAR_FILE_PATH=ToDoList-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE_PATH} todo.jar
ENTRYPOINT ["java", "-jar", "/todo.jar"]
EXPOSE 4040 9999
CMD ["cmd 1"]


#ARG JAR_FILE_PATH=target/ToDoList-0.0.1-SNAPSHOT.jar

# We let Docker copy our jar file into the image.
#COPY target/ToDoList-0.0.1-SNAPSHOT.jar ToDoList-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE_PATH} app.jar