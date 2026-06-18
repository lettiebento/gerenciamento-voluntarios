#!/usr/bin/env bash
set -e
command -v java >/dev/null || { echo "Java 17 ou superior não encontrado."; exit 1; }
command -v mvn >/dev/null || { echo "Maven não encontrado."; exit 1; }
echo "Iniciando em http://localhost:8080"
mvn spring-boot:run
