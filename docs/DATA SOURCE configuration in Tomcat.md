#CRIAR no-jta DATA SOURCE IN TOMCAT


1. No java web project

webapp/META-INF/context.xml

```

	<?xml version="1.0" encoding="UTF-8"?>
	<Context>
	   
	   <!-- disable storage of sessions across restarts --> 
	
	
	   <Manager pathname=""/>
	   	<!--CDI configuration for non J2EE servers-->
	   <Resource name="BeanManager" auth="Container" type="javax.enterprise.inject.spi.BeanManager" factory="org.jboss.weld.resources.ManagerObjectFactory"/>
	   <!-- Uncomment to enable injection into Servlets, Servlet Listeners and Filters in Tomcat -->
	   <!-- <Listener className="org.jboss.weld.environment.tomcat.WeldLifecycleListener"/>-->
	   
	   <!-- Data source configuration: name- is the name data source  -->
	    <Resource name="jdbc/marketplaceDB" auth="Container" type="javax.sql.DataSource"
	               maxTotal="100" maxIdle="30" maxWaitMillis="10000"
	               username="root" password="root" driverClassName="com.mysql.jdbc.Driver"
	               url="jdbc:mysql://localhost:3306/marketplaceDB"/>
	   
	</Context>

```


2. Adicionar o Driver jar à pasta lib do Tomcat tomcat/lib, colar com.mysql.jdbc.Driver .jar


3. Para que persistence.xml consiga Obter o resurso dataSource ele precisa ser indicado no web.xml
 
```

	<resource-ref>
			<res-ref-name>jdbc/marketplaceDB</res-ref-name>
			<res-type>javax.sql.DataSource</res-type>
			<res-auth>Container</res-auth>
	</resource-ref>
```

4. Agora é possivel alterar o ficheiro persistence.xml, removendo as definições de conecção

```
	
	<?xml version="1.0" encoding="UTF-8"?>
	<persistence version="2.0"
		xmlns="http://java.sun.com/xml/ns/persistence" 				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xsi:schemaLocation="http://java.sun.com/xml/ns/persistence 				http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
	
		<persistence-unit name="marketplacePU" transaction-type="RESOURCE_LOCAL">
			<non-jta-data-source>java:comp/env/jdbc/marketplaceDB</non-jta-data-source>
			<properties>
				<!-- <property name="javax.persistence.jdbc.url"
					value="jdbc:mysql://localhost:3306/marketplaceDB" />
				<property name="javax.persistence.jdbc.user" value="root" />
				<property name="javax.persistence.jdbc.password" value="password" />
				<property name="javax.persistence.jdbc.driver" 									value="com.mysql.jdbc.Driver" /> -->
				
				<property name="hibernate.dialect" 									value="org.hibernate.dialect.MySQLDialect" />
				
				<property name="javax.persistence.schema-generation.database.action" 									value="drop-and-create" />
				<property name="javax.persistence.schema-generation.create-source" 									value="metadata" />
				<property name="javax.persistence.sql-load-script-source" 									value="META-INF/sql/carregar-dados.sql"/>
				
				<property name="hibernate.hbm2ddl.auto" value="update" />
				<property name="hibernate.show_sql" value="true" />
				<property name="hibernate.format_sql" value="true" />
				
				<property name="hibernate.cache.use_second_level_cache" value="true"/>
				<property name="hibernate.cache.use_query_cache" value="true" />
				<property name="hibernate.cache.region.factory_class" 										
						value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>
			</properties>
		</persistence-unit>
	
	</persistence>

```

6. Quando se utiliza um Data source, não mais é necessario a utilização da poll connection API como (c3p0-hibernate), deve-se portanto remover as configurações dessa API do ficheiro persistence.xml


NOTE: the hibernate.core.jar 4.3.5 tem um bug reportado em que não consegue encontrar o Datasource por JNDI. Esse bug foi corregido nas versões seguintes.