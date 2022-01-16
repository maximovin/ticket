FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/ticket-1.2.3.jar ticket.jar
ENTRYPOINT ["java","-jar","/ticket.jar"]