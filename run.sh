#!/usr/bin/env bash

echo "////////////////////"
echo "Preparando ambiente com docker compose"
echo "////////////////////"
sudo docker-compose up -d

echo "////////////////////"
echo "Aguarde os containers subirem"
echo "////////////////////"
sleep 15

echo "////////////////////"
echo "Build da aplicação"
echo "////////////////////"
./gradlew clean build -x test

echo "////////////////////"
echo "Start da aplicação"
echo "////////////////////"
java -jar build/libs/subscription-0.0.1-SNAPSHOT.jar &

PID=$!

# Aguarda a aplicação subir
sleep 25

echo "////////////////////"
echo "Enviando requisições via curl"
echo "////////////////////"
sh send_post_subscriptions.sh


echo "////////////////////"
echo "Pressione uma tecla para finalizar o bash"
echo "////////////////////"
read -r REPLY

kill -9 $PID