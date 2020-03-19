package config;

import java.io.IOException;
import java.util.Properties;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

public class DBUnitConfig extends DBTestCase {

    protected IDatabaseTester tester;
    protected IDataSet beforeData;
    private final Properties properties;

    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(properties.getProperty("spring.datasource.driverClassName"),
                properties.getProperty("spring.datasource.url"),
                properties.getProperty("spring.datasource.username"),
                properties.getProperty("spring.datasource.password"));
    }

    public DBUnitConfig(String name) {
        super(name);
        properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, properties.getProperty("spring.datasource.driverClassName"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, properties.getProperty("spring.datasource.url"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, properties.getProperty("spring.datasource.username"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, properties.getProperty("spring.datasource.password"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }

    @Override
    protected IDataSet getDataSet() {
        return beforeData;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }
}
