FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ${rootArtifactId}-web/target/${rootArtifactId}-api-service.jar app.jar
ENV TZ Asia/Shanghai
EXPOSE ${appPort}
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
