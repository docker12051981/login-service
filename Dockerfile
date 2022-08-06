FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} login-service.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo1:27017,mongo2:27027,mongo3:27037/?replicaSet=rs0&retryWrites=true/user_db","-jar","/login-service.jar"]
EXPOSE 9004