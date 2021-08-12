FROM moxm/java:1.8-full
MAINTAINER youshaojunde@163.com
ENV TZ=Asia/Shanghai
WORKDIR /home/test
ARG VERSION=0.0.1
ARG JAR_FILE=target/producer-${VERSION}.jar
COPY ${JAR_FILE} producer-${VERSION}.jar
EXPOSE 8081
ENTRYPOINT  java -jar producer-${VERSION}.jar &