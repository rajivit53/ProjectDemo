# Guest Book Sample Application (v1.0)
## Running guestbook locally
Guestbook is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:

```
git clone https://github.com/rajivit53/ProjectDemo.git
cd guestbook
./mvnw package
java -jar target/*.jar
```

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

You can then access guestbook here: http://localhost:8080/

## In case you find a bug/suggested improvement for Guestbook
Our issue tracker is available here: https://github.com/rajivit53/ProjectDemo/blob/master/guestbook/issuetracker


## Database configuration

1) Download and install the MySQL database (e.g., MySQL Community Server 8.x.x),
   which can be found here: https://dev.mysql.com/downloads/. 

2) Create the guest\_directory database and user by executing the "sqlscript/guest_directory.sql"
   scripts.

3) Create user "guest/guest" user/pass in mysql for creating connection with mysql db. or you can modify the user/pass in application.properties & persistence-mysql.properties file to use exisiting user i.e root.


## Working with Guestbook in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. 
  * [Spring Tools Suite](https://spring.io/tools) (STS)

### Steps:

1) On the command line
```
git clone https://github.com/rajivit53/ProjectDemo.git
```
2) Inside Eclipse or STS
```
File -> Import -> Maven -> Existing Maven project
```

Run the application main method by right clicking on it and choosing `Run As -> Java Application` or `Run As -> Spring Boot App`.

####Login to Guestbook Sample Application

1) Pre configured user are provided inside sqlscript with following roles, which can be used for accessing the application.  

```
User   | Pass    | Role  
rajiv	| test123 | [ROLE_USER]     
ansh	| test123 | [ROLE_ADMIN]   
ayush	| test123 | [ROLE_NA]   
```

#####Details of Usage and Print screen is provided inside doc/guestbook.docx   

<br><br>


## Technology stack used in application

##### Spring Boot
##### Spring boot modules/dependency used in guest application
* spring-boot-starter-actuator
* spring-boot-starter-data-jpa
* spring-boot-starter-security
* spring-boot-starter-thymeleaf
* thymeleaf-extras-springsecurity5
* spring-boot-starter-web
* spring-boot-devtools
* mysql-connector-java
* com.mchange
* spring-boot-starter-test
* mockito-core

##### Bootstrap css 4.x.x used for style.
```
https://getbootstrap.com/  
https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css
```
##### Java 1.8.
```
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
```
##### MySql 8.x.x.
```
https://www.mysql.com/downloads/
```


<hr>



