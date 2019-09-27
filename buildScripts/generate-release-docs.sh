./gradlew clean dokka asciidoctor
mkdir -p build/docs/latest/reference
mkdir -p build/docs/$(cat version.txt)/reference

cp -R build/generated-docs/api build/docs/latest/api
cp -R build/generated-docs/api build/docs/$(cat version.txt)-SNAPSHOT/api
cp -R docs/slack-spring-boot-docs/build/generated-docs/html5/. build/docs/$(cat version.txt)/reference
cp -R docs/slack-spring-boot-docs/build/generated-docs/html5/. build/docs/latest/reference



