#!/bin/sh

if [ -z "$1" ]
  then
    echo "No argument supplied."
    echo "Maybe you do not know how to use me."
    echo "To find it out, I would recommand a little inspection :)"
else
    echo "Let's go for mowing ! "
    java -jar ./mowitnow-1.0-SNAPSHOT-jar-with-dependencies.jar "$@"	
fi
