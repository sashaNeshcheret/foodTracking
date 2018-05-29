package ua.trackingFood;

import ua.trackingFood.exception.ConnectionException;
import ua.trackingFood.transactions.ConnectionWrapper;
import ua.trackingFood.transactions.TransactionManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class InMemoryDBManager {

    private static final String CREATE_DB_SCRIPT_PATH =
            "src\\main\\resources\\CreateScriptDB.sql";
    private static final String INSERT_DEFAULTS_SCRIPT_PATH =
            "src\\main\\resources\\CreateTestDataDB.sql";

    public InMemoryDBManager() throws SQLException, IllegalArgumentException {
        initializeDB();
    }

    private void initializeDB() throws SQLException {
        executeSQLScriptsFromFile(CREATE_DB_SCRIPT_PATH);
        insertDefaultData();
    }

    public void insertDefaultData() throws SQLException, IllegalArgumentException {
        executeSQLScriptsFromFile(INSERT_DEFAULTS_SCRIPT_PATH);
    }

    public void truncateTable(String tableName) throws SQLException {
        try (ConnectionWrapper conn = TransactionManager.getConnection()) {

            String sqlTruncate = "DELETE FROM " + tableName + ";";
            conn.preparedStatement(sqlTruncate).execute();
        } catch (SQLException | ConnectionException e) {
            throw new SQLException("FAILED TO TRUNCATE TABLE");
        }
    }


    public static void executeSQLScriptsFromFile(String filePath) throws SQLException, IllegalArgumentException{
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

    public ConnectionWrapper getConnection() {
        ConnectionWrapper conn = null;
        //log.info("Trying to get connection form dataSource");
        try {
            conn = TransactionManager.getConnection();
        //    log.info("Got connection from dataSource");
        } catch (ConnectionException e) {
        //    log.error("Failed to get connection from dataSource: " + e.getMessage());
        }
        return conn; /*throw*/
    }
}
