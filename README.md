Example Spring Boot MVC Application
===================================

The article: [http://kielczewski.eu/2014/04/spring-boot-mvc-application/](http://kielczewski.eu/2014/04/spring-boot-mvc-application/)

Also check out: [http://kielczewski.eu/2014/04/developing-restful-web-service-with-spring-boot/](http://kielczewski.eu/2014/04/developing-restful-web-service-with-spring-boot/)

This branch uses Bone CP to make a connection pool to Mysql, as described here: [http://kielczewski.eu/2014/05/database-connection-pooling-with-bonecp-in-spring-boot-application/](http://kielczewski.eu/2014/05/database-connection-pooling-with-bonecp-in-spring-boot-application/)

Requirements
------------
* [Java Platform (JDK) 7](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)

Quick start
-----------
1. `mvn package`
2. `java -jar target/example-spring-boot-mvc-1.0-SNAPSHOT.war`
3. Point your browser to [http://localhost:8080/user_list.html](http://localhost:8080/user_list.html)