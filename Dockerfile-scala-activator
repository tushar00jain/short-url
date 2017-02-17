From openjdk:latest

RUN \
    curl -O http://downloads.typesafe.com/typesafe-activator/1.3.12/typesafe-activator-1.3.12.zip && \
    unzip typesafe-activator-1.3.12.zip -d / && \
    rm typesafe-activator-1.3.12.zip

RUN chmod u+x activator-dist-1.3.12/bin/activator

ENV PATH $PATH:/activator-dist-1.3.12/bin

EXPOSE 9000 9000

RUN mkdir project
