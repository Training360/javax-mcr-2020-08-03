FROM adoptopenjdk:13-jre-hotspot

RUN mkdir /opt/app
ADD target/employees-micro-thorntail.jar /opt/app/employees.jar
CMD ["ulimit", "-n", "8192"]
CMD ["java", "-Djava.net.preferIPv4Stack=true", "-Djava.net.preferIPv4Addresses=true", "-jar", "/opt/app/employees.jar"]
