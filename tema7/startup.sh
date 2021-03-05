#!/bin/bash

verifyDocker() {
    if [ "$(docker ps -q -f name=$dockername)" ]; then
        if [ "$(docker ps -aq -f status=exited -f name=$dockername)" ]; then
            docker rmi -f $dockername
        else
            echo "Dockers are running, please run stop.sh before startup.sh"
            exit 1
        fi
    fi
}

verifyDocker "twitterDocker"
verifyDocker "githubDocker"
verifyDocker "conDocker"

echo
echo "build twitter Service"
echo

cd twitter
./gradlew clean build

echo
echo "Building twitter service docker"

docker build -t ilegra/tema7twitter .

echo
echo "Starting twitter docker"

docker run -d --name twitterDocker -p 8081:8081 --network=host --rm ilegra/tema7twitter

cd ..

echo  
echo "Twitter service is running on port 8081"



#####################
echo
echo "build github Service"
echo

cd github
./gradlew clean build

echo
echo "Building github service docker"

docker build -t ilegra/tema7github .

echo
echo "Starting github docker"

docker run -d --name githubDocker -p 8082:8082 --network=host --rm ilegra/tema7github

cd ..

echo  
echo "Github service is running on port 8082"




#####################
echo
echo "build connector Service between twitter and github"
echo

cd connection
./gradlew clean build

echo
echo "Building connector service docker"

docker build -t ilegra/tema7con .

echo
echo "Starting Connector docker"

docker run -d --name conDocker -p 8080:8080 --network=host --rm ilegra/tema7con

cd ..

echo  
echo "Connector service is running on port 8080"


