FROM openjdk:17-slim

ENV TOMCAT_VERSION 9.0.93
ENV CATALINA_HOME /opt/tomcat

WORKDIR /opt/tomcat

RUN apt-get update && apt-get install -y wget unzip


RUN wget https://dlcdn.apache.org/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz \
    && tar -xzf apache-tomcat-${TOMCAT_VERSION}.tar.gz --strip-components=1 -C ${CATALINA_HOME} \
    && rm apache-tomcat-${TOMCAT_VERSION}.tar.gz

RUN apt-get remove -y wget unzip \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*


COPY tomcat/conf/* ${CATALINA_HOME}/conf/
COPY target/SocialNetworkBE-1.0-SNAPSHOT.war ${CATALINA_HOME}/webapps/

EXPOSE 8080

CMD ["/opt/tomcat/bin/catalina.sh" ,"run"]