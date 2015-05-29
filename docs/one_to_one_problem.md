# ONE TO ONE LAZY - COM ANOTAÇÕES
--------------------------------------------------------------------

```
		
		
		@Entity
		@Table(name="detail_table")
		public class Detail implements Serializable {
		
			private static final long serialVersionUID = 1L;
			
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private Long id;
			
			@OneToOne
			@JoinColumn(name="code_user")
			private User user;
		}
		
		
		@Entity
		@Table(name = "user_table")
		public class User implements Serializable, FieldHandled {
		
			private static final long serialVersionUID = 1L;
		
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long code;
			
			private FieldHandler handler;
			
			@OneToOne(mappedBy = "user", fetch=FetchType.LAZY)
			@LazyToOne(LazyToOneOption.NO_PROXY)
			private Detail detail;
			
			//---------------------------------------
			public Endereco getDetail() {
				if (this.handler != null) {
					return (Detail) this.handler.readObject(this, "detail", detail);
				}
				
				return detail;
			}
		
			public void setEndereco(Detail detail) {
				if (this.handler != null) {
					this.endereco = (Endereco) this.handler.writeObject(this, "detail"
							, this.detail, detail);
				}
				
				this.detail = detail;
			}
			
			@Override
			public void setFieldHandler(FieldHandler handler) {
				this.handler = handler;
			}
		
			@Override
			public FieldHandler getFieldHandler() {
				return this.handler;
			}
		}

```

# ONE TO ONE LAZY - COM INSTRUMENTAÇÃO
--------------------------------------------------------------------

##########
###in the Pom file
---------------------------------------------

```

	<build>
			<plugins>
				<plugin>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>3.0</version>
					<configuration>
						<source>1.7</source>
						<target>1.7</target>
					</configuration>
				</plugin>
				<plugin>
			        <artifactId>maven-antrun-plugin</artifactId>
			        <version>1.7</version>
			        <executions>
			          <execution>
			            <id>Instrument domain classes</id>
			            <configuration>
			              <tasks>
			                <taskdef name="instrument"
			                  classname="org.hibernate.tool.instrument.javassist.InstrumentTask">
			                  <classpath>
			                    <path refid="maven.dependency.classpath" />
			                    <path refid="maven.plugin.classpath" />
			                  </classpath>
			                </taskdef>
			                <instrument verbose="true">
			                  <fileset dir="${project.build.outputDirectory}">
			                    <include name="**/entities/**/*.class" />
			                  </fileset>
			                </instrument>
			              </tasks>
			            </configuration>
			            <phase>process-classes</phase>
			            <goals>
			              <goal>run</goal>
			            </goals>
			          </execution>
			        </executions>
			      </plugin>
			</plugins>
		</build>
	
	
```
--------------------------------------------------------------

```
	
	@Entity
	@Table(name="detail_table")
	public class Detail implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@OneToOne
		@JoinColumn(name="code_user")
		private User user;
	}
	
	
	@Entity
	@Table(name = "user_table")
	public class User implements Serializable {
	
		private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long code;
		
		private FieldHandler handler;
		
		@OneToOne(mappedBy = "user", fetch=FetchType.LAZY)
		@LazyToOne(LazyToOneOption.NO_PROXY)
		private Detail detail;
		
		//---------------------------------------
		public Endereco getDetail() {
			return this.detail;
		}
	
		public void setEndereco(Detail detail) {
			this.detail = detail;
		}
		
		
	}

```
