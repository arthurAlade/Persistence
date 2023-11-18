#!/bin/bash

# Source the .env file to load environment variables
if [ -f .env ]; then
    source .env
else
    echo "Error: .env file not found."
    exit 1
fi

mvn clean verify sonar:sonar \
  -Dsonar.projectKey=fr.uga.miage.m1:Persistence_g1_2 \
  -Dsonar.host.url=http://im2ag-sonar.u-ga.fr:9000 \
  -Dsonar.login=$sonarToken