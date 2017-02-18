FROM tushar00jain/scala-activator-node:latest

ADD . /project 
WORKDIR /project/short-url

RUN activator compile
