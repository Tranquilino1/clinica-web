#!/bin/bash
if [ ! -f /data/clinica_aauca.db ]; then
    echo "Initializing persistent database from bundled data..."
    cp /app/clinica_aauca.db /data/clinica_aauca.db
fi
exec java -jar app.jar
