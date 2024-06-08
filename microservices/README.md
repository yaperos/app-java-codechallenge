## Composición del proyecto
El presente proyecto esta conformado por tres módulos

```
1. transaction-gateway
2. transaction-management
3. fraud-evaluation
```

## Detalle de Módulos

* transaction-gateway: Es el orquestador de las funcionalidades hacia el cliente. Utiliza GraphQL
* transaction-management: Microservicio dedicado para el mantenimiento de las transacciones en la BD.
* fraud-evaluation: Microservicio dedicado a la evaluación de fraudes de las transacciones registradas.

## Pasos a seguir para compilar la aplicación

1. Ejecutar el comando `docker-compose up -d` en la carpeta raiz del proyecto
2. Crear base de datos **bd_challenge**
3. Ejecutar script **01_init_query.sql** de la carpeta _/script_
4. Compilar cada uno de los módulos listados previamente



## Detalle de Funcionalidades

El microservicio transaction-gateway expone las siguientes funcionalidades:

1. Query
   - getAllTransactions(limit, offset)
   - getTransactionByCode(transactionCode)


2. Mutation
   - createTransaction(transactionBody)


