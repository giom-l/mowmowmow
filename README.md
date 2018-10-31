#Assembly
To assemble the project, simply run

    ``` mvn clean package ```

Note that will build jars and docker image (gocho/mowitnow).


#Usage 
## You can use the program by several ways :
 - Launch it locally with java
 - Launch a docker image
 
## Local java
 You need to provide 1 argument (which contains the mowers parameters)
 It can be provided as a file
 
    ``` java -jar mowitnow-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp/inputConf ```
 
 or as input on command line.
 In this case, please pay attention to add "\n" to separate lines
 
  ``` java -jar mowitnow-1.0-SNAPSHOT-jar-with-dependencies.jar "5 5\n1 2 N\nGAGAGAGAA" ```
 
## docker image
 In the same manner as in local, you can run the docker image with 2 parameters
    as input on command line
  
  ``` docker run gocho/mowitnow:1.0-SNAPSHOT "5 5\n1 2 N\nGAGAGAGAA" ```
        
   as a file
        in this case, you have to link the file between your local and the container.
        It can be done as follows 
   
   ``` docker run --mount type=bind,source="$(pwd)",target=/app gocho/mowitnow:1.0-SNAPSHOT "app/<filename>" ```
   
