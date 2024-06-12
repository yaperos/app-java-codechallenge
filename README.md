
# Yape Challenge - Karl Renzo Alcala Paucar

## Description

### Arquitectura del Proyecto

Para la solución a este problema se decidió implementar Clean Architecture el cual se encuentra definido en el libro Clean Architecture por Robert C. Martin, este tipo de arquitectura trata de englobar las arquitecturas previamente existentes tales como Hexagonal, Onion, etc. Y nos brinda ciertas ventajas tales como: facilidad para realizar tests, independencia de frameworks, independencia de la base de datos, independencia de cualquier agente externo y independencia de UI.

<img width="1047" alt="Screenshot 2024-06-11 at 9 12 45 PM" src="https://github.com/RenzoAlcala/app-java-codechallenge/assets/13078342/faadc8f3-9deb-4a0d-99d8-afe1eb9af00d">

### Estructura del Proyecto
El proyecto ha sido dividido en 3 capas:

- Entities: en esta capa de almacenan todas las entidades de negocio (estos pueden ser simples estructuras de datos con métodos), son los componentes de más alto nivel, debido a esto, son los que muy probablemente no cambien debido a cambios externos.
- Uses cases: en esta capa se encuentran las reglas de negocio de la aplicación, esto significa que los componentes en esta capa se encargan de administrar las entidades de negocio y el flujo de los datos.
- Infrastructure: en esta capa tenemos todos los componentes de mas bajo nivel tales como componentes de base de datos, framework GraphQL, framework RestController, por los que estos componentes estan destinados como conexión con otras aplicaciones externas.

### Componentes de la solución

- Antifraud: este es un microservicio construido usando Spring Boot el cual tiene como objetivo validar el monto de las transacciones creadas en el componente Transaction, este componente es un Consumer Kafka y recibe los mensajes debido a que esta suscrito al topic "antifraud". Después de realizar la validación, mandara un mensaje al topic "transaction" para actualizar el estado de la transacción "APPROVED" or "REJECTED", esto lo logra debido a que también es un Producer kafka.

- Transaction: este es un microservicio construido con Spring Boot el cual tiene como objetivo realizar distintas operaciones a las transacciones, tiene métodos para crear, modificar estado y obtener una transacción. Tiene una conexión a la base de datos Postgres para realizar dichas operaciones. Este microservicio es un Producer Kafka porque durante el proceso de crear una transacción, este componente envia un mensaje al topic "antifraud" para que el microservicio Antifraud valide el monto de la transacción. Además este microservicio se comporta como un Consumer para que pueda recibir las peticiones de modificación de estado que envia el componente "antifraud". 

- Kafka: este componente es utilizado para la comunicación entre el microservicio de Antifraud y Transaction. Además, utiliza 2 topics: "antifraud" y "transaction", el primero sirve para validar el monto de las transacciones, el consumer es el microservicio Antifraud, el segundo es utilizado para actualizar el estado de la transacción, el consumer de este topic es el microservicio de "Transaction".

- Postgres: es una base de datos relacional que servirá para persistir las transacciones, contendrá una sola tabla Transaction con todas las columnas necesarias para guardar la información de la transacción. El microservicio de Transaction se conectara a esta base de datos utilizando un pool de conexiones que sera administrada por el framework HikariCP.

- Redis: Componente que funcionará como cache para la operación de consultar transacciones.

<img width="1348" alt="Screenshot 2024-06-11 at 8 45 29 PM" src="https://github.com/RenzoAlcala/app-java-codechallenge/assets/13078342/24d10d78-a7df-4a6e-95ea-03a1582613f4">


### Endpoints

Se cuentan con los siguientes recursos en GraphQL:

- Mutation: createTransaction: Crear transaction y validarlo
- Query: getTransaction: Obtener una transaction por código

## Tech Stack

**Server:** Spring boot, GraphQL, Kafka, Gradle, Postgres, Lombok, JUnit, HikariCP


## Levantar proyecto localmente

Clone the project

```bash
  git clone https://github.com/RenzoAlcala/app-java-codechallenge.git
```

Go to the project directory

```bash
  cd app-java-codechallenge
```

Start all the components

```bash
  docker-compose up
```


## Correr tests

To run tests, run the following command

```bash
  ./gradlew clean test --info
```

## Requisitos

Tener instalado docker.

## Autor

- Karl Renzo Alcala Paucar

