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
  java -jar payara-micro-5.2020.4.jar --deploy target\ps.war
```
Point your browser to http://127.0.0.1:8080/ps
