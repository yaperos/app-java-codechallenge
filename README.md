
# Yape Challenge - Karl Renzo Alcala Paucar

## Tech Stack

**Server:** Spring boot, GraphQL, Kafka, Gradle, Postgres, Lombok, JUnit, HikariCP


## Description

### Arquitectura del Proyecto

Para la solucion a este problema se decidió implementar Clean Architecture el cual se encuentra definido en el libro Clean Architecture por Robert C. Martin, este tipo de arquitectura trata de englobar las arquitecturas previamente existentes tales como Hexagonal, Onion, etc. Y nos brinda ciertas ventajas tales como: facilidad para realizar tests, independencia de frameworks, independencia de la base de datos, independencia de cualquier agente externo y independencia de UI. 

### Estructura del Proyecto
El proyecto ha sido dividido en 3 capas:

- Entities: en esta capa de almacenan todas las entidades de negocio (estos pueden ser simples estructuras de datos con metodos), son los componentes de mas alto nivel, debido a esto, son los que muy probablemente no cambien debido a cambios externos.
- Uses cases: en esta capa se encuentran las reglas de negocio de la aplicacion, esto significa que los componentes en esta capa se encargan de administrar las entidades de negocio y el flujo de los datos.
- Infrastructure: en esta capa tenemos todos los componentes de mas bajo nivel tales como componentes de base de datos, framework GraphQL, framework RestController, por los que estos componentes estan destinados como conexion con otras aplicaciones externos.

### Componentes de la solucion

- Antifraud: este es un microservicio construido usando Spring Boot el cual tiene como objetivo validar el monto de las transacciones creadas en el componente Transaction, este componente es un Consumer Kafka y recibe los mensajes debido a que esta suscrito al topic "antifraud". Despues de realizar la validacion, mandara un mensaje al topic "transaction" para actualizar el estado de la transaccion "APPROVED" or "REJECTED", esto lo logra debido a que tambien es un Producer kafka.

- Transaction: este es un microservicio construido con Spring Boot el cual tiene como objetivo realizar distintas operaciones a las transacciones, tiene metodos para crear, modificar estado y obtener una transaccion. Tiene una conexion a la base de datos Postgres para realizar dichas operaciones. Este microservicio es un Producer Kafka porque durante el proceso de crear una transaccion, este componente envia un mensaje al topic "antifraud" para que el microservicio Antifraud valide el monto de la transaccion. Ademas este microservicio se comporta como un Consumer para que pueda recibir las peticiones de modificacion de estado que envia el componente "antifraud". 

- Kafka: este componente es utilizado para la comunicacion entre el microservicio de Antifraud y Transaction. Ademas, utiliza 2 topics: "antifraud" y "transaction", el primero sirve para validar el monto de las transacciones, el consumer es el microservicio Antifraud, el segundo es utilizado para actualizar el estado de la transaccion, el consumer de este topic es el microservicio de "Transaction".

- Postgres: es una base de datos relacional que servira para persistir las transacciones, contendra una sola tabla Transaction con todas las columnas necesarias para guardar la informacion de la transaccion. El microservicio de Transaction se coneectara a esta base de datos utilizando un pool de conexiones que sera administrada por el framework HikariCP.

- Redis: Componente que funcionara como cache para la operacion de consultar transacciones.

### Endpoints

Se cuentan con los siguientes recursos en GraphQL:

- Mutation: createTransaction: Crear transaction y validarlo
- Query: getTransaction: Obtener una transaction por codigo

## Levantar proyecto localmente

Clone the project

```bash
  git clone
```

Go to the project directory

```bash
  cd my-project
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
