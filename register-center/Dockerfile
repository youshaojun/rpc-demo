FROM moxm/java:1.8-full
MAINTAINER youshaojunde@163.com
ENV TZ=Asia/Shanghai
WORKDIR /home
ARG VERSION=0.0.1
ARG JAR_FILE=target/register-center-${VERSION}.jar
COPY ${JAR_FILE} register-center.jar
EXPOSE 8088
ENTRYPOINT ["java", "-Xms128m", "-Xmx128m", "-jar", "register-center.jar"]