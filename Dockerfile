FROM java:8
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 9DA31620334BD75D9DCB49F368818C72E52529D4; echo "deb http://repo.mongodb.org/apt/debian jessie/mongodb-org/4.0 main" | tee /etc/apt/sources.list.d/mongodb-org-4.0.list; apt-get update; apt-get install -y mongodb-org
VOLUME ["/tmp","/home"]
ADD target/restservicedashboard-0.0.1-SNAPSHOT.war /app.war
RUN bash -c 'touch /app.war'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war","--spring.config.location=file:/home/application.properties"]

