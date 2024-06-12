# Getting Started

Cada microservicio se ha construido siguiendo el patrón de diseño "Arquitectura Hexagonal"

### Microservicios

* **transaction-bff:** Backend for FrontEnd, usando GraphQL.
* **transaction-ms:** Creación, actualización y consulta de transacciones. 
  * Envía peticiones asíncronas hacia el microservicio antifraud-ms(Evaluación de la transacción), mediante Apache Kafka
  * Recibe peticiones asíncronas con el estado final de la transacción, mediante Apache Kafka
* **antifraud-ms:** Analiza la transacción recibida y setea el correcto estado de la ella.

### Instalación

Seguir los siguientes pasos.

1. Situarse en el file \app-java-codechallenge\docker-compose.yml y ejectuar: docker compose up
2. Crear Base de datos: yapebd
3. Ejecutar el siguiente script sql: app-java-codechallenge\script-bd **script-create_table.sql**
3. Crear los siguientes tópicos:
   * kafka-console-producer --broker-list localhost:9092 --topic **topic-transaction-update**
   * kafka-console-producer --broker-list localhost:9092 --topic **topic-antifraud-review**
4. Ejecutar los microservicios:
   * transaction-ms
   * antifraud-ms
   * transaction-bff
5. Abrir navegador y escribir: http://localhost:8090/graphiql?path=/graphql
6. Existen dos llamados de recursos:
   * **(Mutation) register**: Proceso de registro y evaluación de transacciones
   * **(Query) getTransactionByCode**: Busca el transaction según el code
   * **(Query) getAllTransactions**: Consigue todas las transacciones
7. En el PR se adjuntan pantallas de peticiones y resultados esperados




    

