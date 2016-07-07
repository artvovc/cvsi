package com.winify.cvsi.dbunit.loader;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.TestContext;

import java.sql.Connection;

/**
 * CustomDbUnitTestExecutionListener --- utility class, custom listener used by DBUnit.
 * 
 */
public class CustomDbUnitTestExecutionListener extends DbUnitTestExecutionListener {

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        super.prepareTestInstance(testContext);

        Connection con = DataSourceUtils
                .getConnection((javax.sql.DataSource) testContext.getApplicationContext().getBean("mDataSource"));
        IDatabaseConnection dbConn = new DatabaseConnection(con, "cvsidbtest");
        DatabaseConfig config = dbConn.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        super.beforeTestMethod(testContext);

        Connection con = DataSourceUtils
                .getConnection((javax.sql.DataSource) testContext.getApplicationContext().getBean("mDataSource"));
        IDatabaseConnection dbConn = new DatabaseConnection(con, "cvsidbtest");
        DatabaseConfig config = dbConn.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
    }
}
