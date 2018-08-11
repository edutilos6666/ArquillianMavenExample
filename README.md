```
//call 
mvn clean test 
//or 
mvn test
//todo at work
-> from pom.xml add profile: arquillian-wildfly-managed
-> arquillian.xml
-> add content of hibernate.cfg.xml into persistence.xml in <properties> tag 
all properties , that are missing "hibernate", add "hibernate" word. 
-> run: mvn clean test or mvn -X clean test 
```
