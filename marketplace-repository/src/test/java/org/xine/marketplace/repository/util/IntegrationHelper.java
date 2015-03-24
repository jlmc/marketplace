package org.xine.marketplace.repository.util;

import java.io.InputStream;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
import org.junit.After;
import org.junit.Before;
/**
 * Integration Helper unit test case class.
 * 
 * This will load the test-data.xml dataset before each test case and will clean the database before each test
 * 
 *
 */
public class IntegrationHelper {

	/** The persistence unit. */
	private String persistenceUnit;

	/** The entity manager factory. */
	private EntityManagerFactory entityManagerFactory;

	/** connection the database connection.*/
	private IDatabaseConnection connection;

	/**The data set.*/
	private IDataSet dataSet;

	/** The entity manager. */
	private EntityManager entityManager;

	/** The tx. */
	private EntityTransaction tx;


	/**
	 * Instantiates a new integration helper.
	 *
	 * @param persistenceUnit the persistence unit
	 */
	public IntegrationHelper(String persistenceUnit){
		this.persistenceUnit = persistenceUnit;


	}

	/**
	 * Current entity manager.
	 *
	 * @return the entity manager
	 */
	public EntityManager currentEntityManager(){
		return this.entityManager;
	}


	/**
	 * Inits the.
	 */
	public void init() {
		try{
			this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistenceUnit);
			this.entityManager = entityManagerFactory.createEntityManager();

			connection = new DatabaseConnection(((SessionImpl) (entityManager.getDelegate())).connection());
			connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());



			FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
			flatXmlDataSetBuilder.setColumnSensing(true);
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test-data.xml");
			this.dataSet = flatXmlDataSetBuilder.build(inputStream);

			this.tx = this.currentEntityManager().getTransaction();
			this.tx.begin();
		}catch(Exception e){
			throw new RuntimeException(e);
		}


	}

	/**
	 * Will clean the dataBase before each test.
	 *
	 * @throws DatabaseUnitException the database unit exception
	 * @throws SQLException the SQL exception
	 */
	@Before
	public void cleanDB() {
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		} catch (DatabaseUnitException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@After
	public  void closeEntityManager() {
		entityManager.close();
		entityManagerFactory.close();
	} 


}
