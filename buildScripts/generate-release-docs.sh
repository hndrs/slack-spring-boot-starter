./gradlew clean asciidoctor
mkdir -p build/docs/latest/reference
mkdir -p build/docs/$(cat version.txt)/reference

cp -R docs/slack-spring-boot-docs/build/generated-docs/html5/. build/docs/$(cat version.txt)/reference
cp -R docs/slack-spring-boot-docs/build/generated-docs/html5/. build/docs/latest/reference



