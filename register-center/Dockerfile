FROM moxm/java:1.8-full
MAINTAINER youshaojunde@163.com
ENV TZ=Asia/Shanghai
WORKDIR /home
COPY target/register-center-*.jar register-center.jar
EXPOSE 8088
ENTRYPOINT ["java", "-Xms128m", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "register-center.jar"]