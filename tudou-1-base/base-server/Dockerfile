FROM java:8
WORKDIR /home/youz/docker/app/
VOLUME ["/tmp"]
EXPOSE 20881
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]