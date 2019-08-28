FROM openjdk:8-jre-alpine
COPY build/libs/*.jar sample-application.jar
CMD java -XX:+PrintFlagsFinal -XX:+UseContainerSupport \
         -jar \
         -Dserver.port=$PORT \
         -Dslack.token=$SLACK_TOKEN \
         -Dslack.application.client-id=$SLACK_CLIENT_ID \
         -Dslack.application.client-secret=$SLACK_CLIENT_SECRET \
         -Dslack.application.signing-secret=$SLACK_SIGNING_SECRET \
         sample-application.jar
