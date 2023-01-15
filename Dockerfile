FROM openjdk:17

COPY target/job-service.jar job-service.jar

ENTRYPOINT ["java","-jar","job-service.jar"]

EXPOSE 8082

