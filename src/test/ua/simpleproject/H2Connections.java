package ua.simpleproject;

import org.h2.jdbcx.JdbcConnectionPool;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.transactions.ConnectionPool;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

class H2Connections {

    private static final String URL = "jdbc:h2:mem:test;MODE=Mysql;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;INIT=CREATE SCHEMA IF NOT EXISTS test";
    public static final JdbcConnectionPool H2_CONNECTION_POOL = JdbcConnectionPool.create(URL,"s","s");

    private void executeSQLScriptsFromFile(String filePath) throws SQLException, IllegalArgumentException{
        File file = new File(filePath);
        try (ConnectionWrapper conn = TransactionManager.getConnection()) {

            String script = new String(Files.readAllBytes(file.toPath()));
            script = script.replaceAll("[\\s]+", " ");
            String[] queries = script.split(";");
            try {
                for (String query : queries) {
                    if (!query.trim().isEmpty()) {
                        conn.preparedStatement(query).execute();
                    }
                }
            } catch (SQLException  e) {
                throw new SQLException(e);
            }
        } catch (IOException | ConnectionException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e){
            throw e;
        }
    }
    public static void truncateTable(String tableName) throws SQLException {
        try (ConnectionWrapper conn = TransactionManager.getConnection()) {

            String sqlTruncate = "DELETE FROM " + tableName + ";";
            conn.preparedStatement(sqlTruncate).execute();
        } catch (SQLException | ConnectionException e) {
            throw new SQLException("FAILED TO TRUNCATE TABLE");
        }
    }
}