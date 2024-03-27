FROM openjdk:21
EXPOSE 8080
ADD target/expense_microservice.jar expense_microservice.jar
ENTRYPOINT [ "java","-jar","/expense_microservice.jar" ]