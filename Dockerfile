FROM jeanblanchard/java:latest

MAINTAINER ZD Smith (subsetpark@gmail.com)

RUN apk update

ADD target/uberjar/clrk-omdb-0.1.0-standalone.jar /srv/clrk-omdb.jar 

EXPOSE 8080                                                    

CMD ["java", "-jar", "/srv/clrk-omdb.jar"]  
