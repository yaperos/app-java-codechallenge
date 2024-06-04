FROM openjdk:11-jre-slim
#COPY wait-for-kafka.sh /wait-for-kafka.sh
COPY target/antifraud-service-0.0.1-SNAPSHOT.jar antifraud-service.jar
ENTRYPOINT ["java", "-jar", "/antifraud-service.jar"]