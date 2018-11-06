
# CS3235 project

## Basic information

ImageHost is accessed at localhost:8080/ImageHost

Selenium is coded in Java 8, packaged using maven 3.3.9 and deployed using Tomcat 8.5.3. 
Spring framework 4.3.0 is used as the programming model. The database used is MySQL 6.3. 

More information about version information could be found in pom.xml.

## Changing the source code

Source code is located at https://192.168.0.11/yuxi/Selenium

If there is a need to modify the source code, follow the steps:

1) Download an IDE. It is recommended to use Intellj IDEA as the IDE platform for modifying the code. Open the project.

2) ***In main/resources/application.properties, ensure that the database connection settings, file locations are exactly the same on the local machine. File locations in the database should match with the local machine. Ensure that the all the setting are correct, and the site is up and running before continuing.

3) Html error codes are handled by tomcat. For developmental purposes, remove the error-code under /conf/web.xml.

3) Modify.

## Deployment

Use the command "mvn package" to create the war file, which would be created at /code/target. Transfer the war to /webapps in Tomcat folder, and startup the service at /bin.


## Updating

There are a few important items that should be noted.

1) Update the items found under pom.xml.
