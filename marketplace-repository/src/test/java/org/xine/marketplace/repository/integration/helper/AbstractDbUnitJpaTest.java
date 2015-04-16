package org.xine.marketplace.repository.integration.helper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
import org.junit.AfterClass;
import org.junit.Before;

/**
 * The Class AbstractDbUnitJpaTest.
 */
public abstract class AbstractDbUnitJpaTest {

    /** The entity manager factory. */
    private static EntityManagerFactory entityManagerFactory;

    /** The connection. */
    private static IDatabaseConnection connection;

    /** The dataset. */
    private static IDataSet dataset;

    /** The entity manager. */
    private static EntityManager entityManager;

    /** The data set path. */
    private static String dataSetPath;

    /**
     * Will load {@code AbstractDbUnitJpaTest#dataSetPath} before each test case
     * must be executed in method markated as BeforeClass .
     * @throws HibernateException
     *             the hibernate exception
     * @throws DatabaseUnitException
     *             the database unit exception
     */
    // @BeforeClass
    protected static void initEntityManager() throws HibernateException, DatabaseUnitException {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        connection = new DatabaseConnection(
                ((SessionImpl) (entityManager.getDelegate())).connection());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new HsqldbDataTypeFactory());

        final FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
        flatXmlDataSetBuilder.setColumnSensing(true);

        try (InputStream dataSet = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(dataSetPath)) {
            dataset = flatXmlDataSetBuilder.build(dataSet);
        } catch (final IOException e) {
            throw new RuntimeException("Load Data Set file ", e.getCause());
        }
    }

    /**
     * Close entity manager.
     */
    @AfterClass
    public static void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * Will clean the dataBase before each test.
     * @throws DatabaseUnitException
     *             the database unit exception
     * @throws SQLException
     *             the SQL exception
     */
    @SuppressWarnings("static-method")
    @Before
    public void cleanDB() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }

    /**
     * Sets the data setpath.
     * @param path
     *            the new data setpath
     */
    public static void setDataSetpath(final String path) {
        AbstractDbUnitJpaTest.dataSetPath = path;
    }

    /**
     * Gets the data setpath.
     * @return the data setpath
     */
    public static String getDataSetpath() {
        return AbstractDbUnitJpaTest.dataSetPath;
    }

    /**
     * Gets the entity manager.
     * @return the entity manager
     */
    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
