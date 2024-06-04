#!/bin/sh

set -e

host="$1"
shift
cmd="$@"

until nc -z "$host" 9092; do
  >&2 echo "Kafka is unavailable - sleeping"
  sleep 5
done
# Crear el tema transactionCreated
kafka-topics.sh --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1 --topic transactionCreated

>&2 echo "Kafka is up - executing command"
exec $cmd
