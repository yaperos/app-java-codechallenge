FROM gradle:jdk17 AS BUILD
WORKDIR /usr/app/
COPY antifraud-yape .
RUN gradle build

FROM eclipse-temurin:17
WORKDIR /usr/app/
COPY --from=BUILD /usr/app/ .
ENTRYPOINT ["java","-jar","build/libs/antifraud-yape-0.0.1-SNAPSHOT.jar"]