# There are two main issues while working with the JSP pages in the Spring Boot project, and these can be solved with the help of these two steps:

## Step 1: Spring Boot is generally used to build the REST apis. So, there is no support for the JSP files. To make it available, we have to add the extensions (Plugins).

## Step 2: Even the Spring Boot's embedded server doesn't compile the JSP pages. In short doesn't have the capability to do so. `Jasper` is the dependency that is used to make it possible. We have to add it in the `pom.xml`.

```xml
<!-- https://mvnrepository.com/artifact/org.apache.tomcat/tomcat-jasper -->
		<dependency>
		    <groupId>org.apache.tomcat</groupId>
		    <artifactId>tomcat-jasper</artifactId>
		    <version>11.0.1</version>
		</dependency>
```