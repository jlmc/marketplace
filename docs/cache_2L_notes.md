 Cache de segundo nivel esta enable ou disable
``` 
	<property name="hibernate.cache.use_second_level_cache" value="true"/>
```
 Cache de segundo nivel para Queries enable ou disable
 ```
 <property name="hibernate.cache.use_query_cache" value="true" />
```


#Hibernate Testing  segundo nivel

```
	
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-testing</artifactId>
			<version>4.3.5.Final</version>
			<scope>compile</scope>
		</dependency>
 <property name="hibernate.cache.region.factory_class" value="org.hibernate.testing.cache.CachingRegionFactory"/>
```

#ENTIDADES		

NOTE: eventualmente entidade será actulizada, embora não frequente.
```

	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
```


NOTE: entidade não sobre nunca actulizaçao
```

		@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
```

NOTE: entidade sofre actuliações
```

	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
```

#ManyToMany ou ManyToOne - cache de colecções
** necessario fazer cache de ambas entidades e tambem da colecção de relacionamento

#PARA CACHE DE QUERIES 

```

		typedQuery.setHint("org.hibernate.cacheable", Boolean.TRUE);
		// typedQuery.unwrap(org.hibernate.Query.class).setCacheable(true)
```

##===============================================================================
PROVEDORES EHCACHE

```

		<dependency>
			<groupId>net.sf.ehcache</groupId>
			<artifactId>ehcache-core</artifactId>
			<version>2.6.11</version>
		</dependency>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-ehcache</artifactId>
			<version>4.3.8.Final</version>
		</dependency>
		 <property name="hibernate.cache.region.factory_class" value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>

```


#eHCACHE FILE,

in resource root folder

```

	<ehcache>
	    <diskStore path="java.io.tmpdir/ehcache" />
	
	    <!--
	        maxElementsInMemory - Quantos elementos vão estar em memoria
	        eternal - os elementos da memoria ficarão nela para sempre [true|false]
	
	        timeToIdleSeconds="60" - if eternal is false, Quanto tempo uma entidade pode ficar em Idle, sem ser utilizada, se não estiver a ser utilizada tem de sair do cache
	       
	        timeToLiveSeconds="120" - if eternal is false, Maximo tempo que uma entidade ficará na Cache,  independentemente de ser consultada ou não overflowToDisk - [true|false] - Quando se atigir o 											maxElementsInMemory, é possivel guardar em disco mais entidade, no caminho definido em diskStore path
	
	        maxElementsOnDisk - Maximos de entidades no HD
	        diskExpiryThreadIntervalSeconds - maximo tempo que uma entidade pode ficar no HD
	
	        memoryStoreEvictionPolicy - algaritmo de retiar, mover as entidades quando der overflow na memoria [LRU-Menos recentemente usado]
	
	        NOTE: diskStore
							        *user.home - User's home directory
							        *user.dir- User's current working directory
							        *java.io.tmpdir - Default temp file path exemplo: C:\Users\[current-user]\AppData\Local\Temp\ehcache
							        *ehcache.disk.store.dir- A system property you would normally specify on the command line—for 
							        example, java -Dehcache.disk.store.dir=/u01/myapp/diskdir.
							        diskStore path="/opt/cache"
	    -->
	
	
	<defaultCache maxElementsInMemory="1000" eternal="false" timeToIdleSeconds="60"
	        timeToLiveSeconds="120" overflowToDisk="true" maxElementsOnDisk="100000"
	        diskExpiryThreadIntervalSeconds="120" memoryStoreEvictionPolicy="LRU" />
	</ehcache>

```
