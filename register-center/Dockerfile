FROM moxm/java:1.8-full
MAINTAINER youshaojunde@163.com
ENV TZ=Asia/Shanghai
WORKDIR /home
ARG VERSION=0.0.1
ARG JAR_FILE=target/register-center-${VERSION}.jar
COPY ${JAR_FILE} register-center-${VERSION}.jar
EXPOSE 8088
ENTRYPOINT sleep 60; java -jar redister-center-${VERSION}.jar &