#!/bin/bash
if [ ! -f ./pom.xml ]
then
    echo "No pom file: ./pom.xml"
    exit 1
fi
if [ ! -f ./target/annotations.jar ]
then
    echo "No jar file: ./target/annotations.jar"
    exit 2
fi
if [ ! -d ./artifacts ]
then
    mkdir artifacts
fi
cp target/annotations.jar artifacts/annotations.jar
cp pom.xml artifacts/pom.xml