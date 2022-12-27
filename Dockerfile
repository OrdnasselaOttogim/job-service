FROM openjdk:11

COPY target/job-service-0.0.1-SNAPSHOT.jar /etc/app.jar

ENTRYPOINT ["java","-jar","/etc/app.jar"]
