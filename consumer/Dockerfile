FROM moxm/java:1.8-full
MAINTAINER youshaojunde@163.com
ENV TZ=Asia/Shanghai
WORKDIR /home
ARG VERSION=0.0.1
ARG JAR_FILE=target/consumer-${VERSION}.jar
COPY ${JAR_FILE} consumer.jar
EXPOSE 8082
ENTRYPOINT ["java", "-Xms128m", "-Xmx128m", "-jar", "consumer.jar"]