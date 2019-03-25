FROM maven:3.6.0-jdk-8-alpine
COPY ./ /workspace
WORKDIR /workspace
EXPOSE 8081
RUN mvn clean install -DskipTests
VOLUME /workspace /root/.m2
ENTRYPOINT ["java","-jar"]