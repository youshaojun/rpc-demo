FROM moxm/java:1.8-full
LABEL maintainer="youshaojunde@163.com"
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m"
WORKDIR /home/test
ARG JAR_FILE=target/consumer.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT sleep 60; java $JAVA_OPTS -jar app.jar &