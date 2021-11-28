# Runtime base Image
FROM openjdk:11

USER root

# Copiere das Java Artefakt aus Build Container
# COPY --from=builder /app/target/app.jar /
COPY  /home/runner/work/camel-swagger-example/camel-swagger-example/target/app.tar.gz /


# Copy assembly and unzip
# COPY   /app/target/app.tar.gz /
RUN tar -xvzf app.tar.gz
RUN rm app.tar.gz

USER java
# verify that all is in place
RUN cd /lib
RUN ls -la /lib/app.jar
RUN ls -la /lib/hawt

# HAWT
EXPOSE 8080

# REST API
EXPOSE 8090


# Startpunkt für Instanziierte Container
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=UTC","-jar","/lib/app.jar"]
