FROM openjdk:8-jre-alpine
EXPOSE 8080
ADD samples/slack-spring-boot-starter-sample/build/libs/slack-spring-boot-starter-sample-1.0.0.M6.jar sample-applicationgit .jar
ENTRYPOINT ["java", "-jar", "sample.jar"]