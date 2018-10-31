FROM openjdk:latest

LABEL "author"="Guilaume Lhermenier"
LABEL "source repository"=""
LABEL "informations"="Mow It Now exercise for Xebia"

WORKDIR /mowitnow
ADD target/mowitnow-1.0-SNAPSHOT-jar-with-dependencies.jar .
ADD mowitnow.sh .
RUN chmod -R 755 *

ENTRYPOINT ["/mowitnow/mowitnow.sh"]
CMD []
