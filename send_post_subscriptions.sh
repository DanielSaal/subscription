#!/usr/bin/env bash

while read line; do
	if [ ! -z "$line" ]; then
		BODY=$(echo $line | sed 's/'\''/\"/g');
		curl -X POST "http://localhost:8080/subscriptions" -H "accept: */*" -H "Content-Type: application/json" -d "$BODY";
	fi
done < notificacoes.txt