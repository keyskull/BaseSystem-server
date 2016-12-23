FROM keyskull/scala:2.11.8
MAINTAINER keyskull <a410615903@gmail.com>

WORKDIR /BaseSystem
COPY target/scala*/server.jar .

CMD scala -J-agentlib:jdwp=transport=dt_socket,address=23111,suspend=n,server=y server.jar
EXPOSE 8333 53