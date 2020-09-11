# history-validator
Pension Statement transaction validator.

## Developing
1. Git clone the repository
2. Install JDK 8
3. If you already Payara Server install skip. Otherwise download Payara micro payara-micro-5.2020.4
4. For building from command line, install maven and set MAVEN_HOME to root folder of your maven
5. Chage directory to source code folder and run:
```bash
  mvn clean package
```
6. if building from IDE, load the pom.xml file with your choiced IDE and run clean.
7. To run type the following in the root folder:
```bash
  java -jar payara-micro-5.2020.4.jar --deploy target\th.war
```
Point your browser to http://127.0.0.1:8080/ps

## Database configuration
The datasource configuration is in src/main/resources/appliction.properties file.

```java
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp

#database configuration
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.datasource.url=jdbc:sqlserver://192.168.99.101:1433;databaseName=EnPowerV4
spring.datasource.username=sa
spring.datasource.password=Db@P@55w0rd
```
Change the IP address in the datasource URL as well as login credentials to reflect your environment.

