#Map an Entity Bean object to an object DTO

Map an Entity Bean object to an object DTO can be difficult when, for example is intended to expose the entities (through WebSerice), and during the serialization process all get the entity run. (LazyInicializationException)


- It can be quite simple if you want the DTO is the same type as the Entity Bean.

## using the next dependency
```
  <!-- Beanlib -->
  <dependency>
  	<groupId>net.sf.beanlib</groupId>
  	<artifactId>beanlib-hibernate</artifactId>
  	<version>5.0.2beta</version>
  </dependency>
  ```

## we can create a copy of the entity without any referency of Hibernate proxys
´´´´

		MyEntity u = this.manager.find(MyEntity.class, 1L);
		assertEquals("Abcd", u.getNane());
		
		Hibernate3DtoCopier copier = new Hibernate3DtoCopier();
		MyEntity copy = copiador.hibernate2dto(u);
		
		assertNull(copy.getEndereco());
```
	
