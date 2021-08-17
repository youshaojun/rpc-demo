FROM moxm/java:1.8-full
MAINTAINER youshaojunde@163.com
ENV TZ=Asia/Shanghai
WORKDIR /home
COPY target/producer-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Xms128m", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]