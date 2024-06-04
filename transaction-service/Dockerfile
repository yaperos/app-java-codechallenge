FROM openjdk:11-jre-slim
#COPY wait-for-kafka.sh /wait-for-kafka.sh
COPY target/transaction-service-0.0.1-SNAPSHOT.jar transaction-service.jar
ENTRYPOINT ["java", "-jar", "/transaction-service.jar"]
