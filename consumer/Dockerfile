FROM moxm/java:1.8-full
MAINTAINER youshaojunde@163.com
ENV TZ=Asia/Shanghai
WORKDIR /home
ARG VERSION=0.0.1
ARG JAR_FILE=target/consumer-${VERSION}.jar
COPY ${JAR_FILE} consumer-${VERSION}.jar
EXPOSE 8082
ENTRYPOINT java -jar consumer-${VERSION}.jar &