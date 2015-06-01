# Primefaces change Theme

The dependencies are:
```

      <groupId>org.primefaces.themes</groupId>
            <artifactId>all-themes</artifactId
            <version>1.0.10</version>
            <scope>compile</scope>
        </dependency>
```
or

```

      <groupId>org.primefaces.themes</groupId>
            <artifactId>bootstrap</artifactId> 
            <version>1.0.10</version>
            <scope>compile</scope>
        </dependency>
 ```
 
 and in the web.xml file, add the following configuration:
 
 
```
		
		<context-param>
	        <param-name>primefaces.THEME</param-name>
	        <param-value>bootstrap</param-value>
	    </context-param>
```

###Notes:


_The addition of the theme dependence was held in maven manually because the central repository apparently have not yet:_

 1. get the jar in the primefaces repository: 
```
 		http://repository.primefaces.org/org/primefaces/themes/
 ```
 
 2. execute the maven command:
  
```
 		mvn install:install-file -Dfile=bootstrap-1.0.10.jar -DgroupId=org.primefaces.themes -DartifactId=bootstrap -Dversion=1.0.10 -Dpackaging=jar
```
 

_Alternatively you can try to add the Primefaces remote repository_
```
  <repositories>
        <repository>
            <id>prime-repo</id>
            <name>PrimeFaces Maven Repository</name>
            <url>http://repository.primefaces.org</url>
            <layout>default</layout>
        </repository>
    </repositories>
```