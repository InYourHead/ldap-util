FROM eclipse-temurin:17-jre-alpine

COPY target/ldap-util-app.jar ldap-util-app.jar

ENTRYPOINT java -jar -Dspring.profiles.active=${ACTIVE_PROFILES:-ldap,ad} ldap-util-app.jar

EXPOSE 8080