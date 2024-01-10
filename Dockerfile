FROM eclipse-temurin:17-alpine

WORKDIR /app

COPY target/TransactionServiceProject-0.0.1-SNAPSHOT.jar /app

EXPOSE 8083

CMD ["java", "-jar", "TransactionServiceProject-0.0.1-SNAPSHOT.jar", "spring.profiles.active=docker"]

