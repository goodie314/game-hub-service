FROM tomcat

RUN rm -rf /usr/local/tomcat/webapps/ROOT/

ADD target/game-hub-service-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war