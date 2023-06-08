FROM java:openjdk-8u111-jre-alpine

MKDIR /app

WORKDIR /app

COPY ./target/karirku-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "karirku-0.0.1-SNAPSHOT.jar"]