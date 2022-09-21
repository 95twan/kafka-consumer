#!/bin/zsh

unzip "$1"

cd ./dummy || exit

gradle build
docker build -t dummy .
docker run -d --name dummy -p 4000:8080 dummy
