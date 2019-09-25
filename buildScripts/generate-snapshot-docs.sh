./gradlew clean dokka asciidoctor
mkdir -p build/docs/latest-SNAPSHOT/reference
mkdir -p build/docs/$(cat version.txt)-SNAPSHOT/reference

cp -R build/generated-docs/api build/docs/latest-SNAPSHOT/api
cp -R build/generated-docs/api build/docs/$(cat version.txt)-SNAPSHOT/api
cp -R docs/slack-spring-boot-docs/build/generated-docs/html5 build/docs/$(cat version.txt)-SNAPSHOT/reference
cp -R docs/slack-spring-boot-docs/build/generated-docs/html5 build/docs/latest-SNAPSHOT/reference



