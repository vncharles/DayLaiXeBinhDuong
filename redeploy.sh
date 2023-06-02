#!/bin.bash
git pull
mvn package
sudo kill -9 $(sudo lsof -t -i:8080)
java -jar target/website-0.0.1-SNAPSHOT.jar &
