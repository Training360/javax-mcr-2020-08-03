# Microservice alkalmazás felépítése Spring Boot keretrendszerrel Docker környezetben

A Training360 hírleveleiben szereplő cikkeket megtaláljátok a [Hírek](https://www.training360.com/hirek)
oldalon.

Az `employees-micro` könyvtárban találjátok a példa alkalmazás Thorntailre épülő megvalósítását.

Az `employees-quarkus` könyvtárban találjátok a példa alkalmazás Quarkus-ra épülő megvalósítását.

A JMS azért nem működött, mert a `spring-boot-starter-artemis` függőség helyett a `spring-boot-starter-activemq`
függőséget húztam be, ami az előző verziója, és ezért nem az Artemis-hez kapcsolódott, hanem indított egy ActiveMQ-t.

Még volt egy hiba, a `JmsConfig` osztályban a `CreateEventCommand`-et kell megadni, mert az Eventstore alkalmazás
ezt a header értéket tudja értelmezni.