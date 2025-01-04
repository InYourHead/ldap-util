FROM ubuntu:24.04

RUN apt update && apt install -y openjdk-17-jdk && apt autoremove -y && apt clean -y

COPY target/ldap-util-app.jar ldap-util-app.jar

ENTRYPOINT java -jar -Dspring.profiles.active=${ACTIVE_PROFILES:-ldap,ad} ldap-util-app.jar

EXPOSE 8080