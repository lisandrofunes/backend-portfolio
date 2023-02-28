FROM amazoncorretto:17.0.6

COPY target/crud-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
