# Instruções para criar o JPA METAMODEL 

1. ADICIONAR A DEPENDENCIA NECESSÁRIA:
```

	<dependency>
	            <groupId>org.hibernate</groupId>
	            <artifactId>hibernate-jpamodelgen</artifactId>
	            <version>4.3.9.Final</version>
	            <scope>compile</scope>
	 </dependency>
```

2. ADICIONANDO A DEPENDENCIA, O METAMODEL É JÁ GERADO, NO ENTATNTO ELE É GERARADO NA PASTA \target\generateclass. 
	Será melhor se o Metamodel fosse enviado para uma pasta espefifica apenas para ele. Isso conseguesse atraves da 
	configuração dos seguintes plugins.

3. CONFIGURAÇÃO DOS PLUGINS

```
	
	  		<build>
		        <plugins>
		            <plugin>
		                <artifactId>maven-compiler-plugin</artifactId>
		                <version>3.0</version>
		                <configuration>
		                    <source>1.8</source>
		                    <target>1.8</target>
		                    <!-- necessary to generate the Metamodel, with this we compile the anotations just one time -->
		                    <compilerArgument>-proc:none</compilerArgument>
		                </configuration>
		            </plugin>
	
	            <!-- metamodel -->
	            <plugin>
	                <groupId>org.bsc.maven</groupId>
	                <artifactId>maven-processor-plugin</artifactId>
	                <version>2.2.4</version>
	                <executions>
	                    <execution>
	                        <id>process</id>
	                        <goals>
	                            <goal>process</goal>
	                        </goals>
	                        <phase>generate-sources</phase>
	                        <configuration>
	                            <outputDirectory>target/metamodel</outputDirectory>
	                        </configuration>
	                    </execution>
	                </executions>
	            </plugin>
	            <plugin>
	                <groupId>org.codehaus.mojo</groupId>
	                <artifactId>build-helper-maven-plugin</artifactId>
	                <version>1.3</version>
	                <executions>
	                    <execution>
	                        <id>add-source</id>
	                        <phase>generate-sources</phase>
	                        <goals>
	                            <goal>add-source</goal>
	                        </goals>
	                        <configuration>
	                            <sources>
	                                <source>target/metamodel</source>
	                            </sources>
	                        </configuration>
	                    </execution>
	                </executions>
	            </plugin>
	        </plugins>
	        <pluginManagement>
	            <plugins>
	                <!--This plugin's configuration is used to store Eclipse m2e settings only. It has no 
	                    influence on the Maven build itself. -->
	                <plugin>
	                    <groupId>org.eclipse.m2e</groupId>
	                    <artifactId>lifecycle-mapping</artifactId>
	                    <version>1.0.0</version>
	                    <configuration>
	                        <lifecycleMappingMetadata>
	                            <pluginExecutions>
	                                <pluginExecution>
	                                    <pluginExecutionFilter>
	                                        <groupId>org.bsc.maven</groupId>
	                                        <artifactId>
	                                            maven-processor-plugin
	                                        </artifactId>
	                                        <versionRange>
	                                            [2.2.4,)
	                                        </versionRange>
	                                        <goals>
	                                            <goal>process</goal>
	                                        </goals>
	                                    </pluginExecutionFilter>
	                                    <action>
	                                        <ignore></ignore>
	                                    </action>
	                                </pluginExecution>
	                                <pluginExecution>
	                                    <pluginExecutionFilter>
	                                        <groupId>
	                                            org.codehaus.mojo
	                                        </groupId>
	                                        <artifactId>
	                                            build-helper-maven-plugin
	                                        </artifactId>
	                                        <versionRange>
	                                            [1.3,)
	                                        </versionRange>
	                                        <goals>
	                                            <goal>add-source</goal>
	                                        </goals>
	                                    </pluginExecutionFilter>
	                                    <action>
	                                        <ignore></ignore>
	                                    </action>
	                                </pluginExecution>
	                            </pluginExecutions>
	                        </lifecycleMappingMetadata>
	                    </configuration>
	                </plugin>
	            </plugins>
	        </pluginManagement>
	    </build>  
    
 ```