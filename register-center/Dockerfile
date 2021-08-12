FROM moxm/java:1.8-full
MAINTAINER youshaojunde@163.com
ENV TZ=Asia/Shanghai
WORKDIR /home/test
ARG VERSION=0.0.1
ARG JAR_FILE=target/redister-center-${VERSION}.jar
COPY ${JAR_FILE} redister-center-${VERSION}.jar
EXPOSE 8088
ENTRYPOINT sleep 60; java -jar redister-center-${VERSION}.jar &