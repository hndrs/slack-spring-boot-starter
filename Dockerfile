FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD client/slack-api-client/build/libs/slack-api-client-1.0.0.M6.jar slack-api-client-1.0.0.M6.jar
ADD client/slack-spring-api-client/build/libs/slack-spring-api-client-1.0.0.M6.jar slack-spring-api-client-1.0.0.M6.jar
ADD client/slack-spring-test-api-client/build/libs/slack-spring-test-api-client-1.0.0.M6.jar slack-spring-test-api-client-1.0.0.M6.jar
ADD data/slack-jackson-dto/build/libs/slack-jackson-dto-1.0.0.M6.jar slack-jackson-dto-1.0.0.M6.jar
ADD data/slack-jackson-dto-test-extensions/build/libs/slack-jackson-dto-test-1.0.0.M6.jar slack-jackson-dto-test-1.0.0.M6.jar
ADD docs/slack-spring-boot-docs/build/libs/slack-spring-boot-docs-1.0.0.M6.jar slack-spring-boot-docs-1.0.0.M6.jar
ADD samples/slack-spring-boot-starter-sample/build/libs/slack-spring-boot-starter-sample-1.0.0.M6.jar sample.jar
ADD starter/slack-spring-boot/build/libs/slack-spring-boot-1.0.0.M6.jar slack-spring-boot-1.0.0.M6.jar
ADD starter/slack-spring-boot-autoconfigure/build/libs/slack-spring-boot-autoconfigure-1.0.0.M6.jar slack-spring-boot-autoconfigure-1.0.0.M6.jar
ADD starter/slack-spring-boot-starter/build/libs/slack-spring-boot-starter-1.0.0.M6.jar slack-spring-boot-starter-1.0.0.M6.jar
ENTRYPOINT ["java", "-jar", "sample.jar"]