./gradlew asciidoctor
mkdir -p build/docs/latest-SNAPSHOT/reference
mkdir -p build/docs/$(cat version.txt)-SNAPSHOT/reference

cp -R docs/slack-spring-boot-docs/build/generated-docs/html5/. build/docs/$(cat version.txt)-SNAPSHOT/reference
cp -R docs/slack-spring-boot-docs/build/generated-docs/html5/. build/docs/latest-SNAPSHOT/reference



