FROM openjdk:8
VOLUME /tmp
EXPOSE 8020
ADD ./target/springboot-servicio-OperationCreditCard-0.0.1-SNAPSHOT.jar service-OperationCreditCard.jar
ENTRYPOINT ["java","-jar","/service-OperationCreditCard.jar"]
