package ua.simpleproject;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.*;
import ua.simpleproject.dao.DAOUsers;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionPool;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;
import static ua.simpleproject.H2Connections.H2_CONNECTION_POOL;
import static ua.simpleproject.H2Connections.truncateTable;
import static ua.simpleproject.InMemoryDBManager.executeSQLScriptsFromFile;

public class UsersJdbcDAOTest {
    static final String USERS_TABLE_NAME = "users";
    //static InMemoryDBManager inmManager;
    static DAOUsers usersDAO;
    static User testUser1, testUser2, nonExistingUser, sameUserFromDB;
    static ConnectionWrapper conn;

    static Logger log = Logger.getLogger(UsersJdbcDAOTest.class.getSimpleName());

    static {
        //new DOMConfigurator().doConfigure("src\\main\\resources\\log4j.xml", LogManager.getLoggerRepository());
    }



    int genId;

    @BeforeClass
    public static void BeforeClass() {
        //nonExistingUser = new User("cashier", "ivan", "ivan", "qwe", "because");
       // testUser1 = new User("cashier", "Maр'яна", "best", "qwerty", "valera");
        //testUser2 = new User("senior_cashier", "sasha", "ALEX", "life", "кошеня");

    }

    @AfterClass
    public static void AfterClass() {

        usersDAO = null;
        //inmManager = null";
        testUser1 = null;
        testUser2 = null;
        nonExistingUser = null;
        sameUserFromDB = null;
    }

    @Before
    public void init() throws SQLException {

        ConnectionPool.getConnectionPool().setDataSource(H2_CONNECTION_POOL);
        try {
            conn = TransactionManager.getConnection();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }

        try {
            executeSQLScriptsFromFile("src\\main\\resources\\CreateTestDB.sql");
            executeSQLScriptsFromFile("src\\main\\resources\\CreateTestDataDB.sql");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        usersDAO = DAOFactory.getDaoFactory().getDaoUsers();
    }

    @After
    public void after() {
        //testUser1.setId(0);
        //testUser2.setId(0);
        //sameUserFromDB = null;
        try {
            conn.close();
            truncateTable(USERS_TABLE_NAME);
        } catch (SQLException e) {
            log.error(e.getMessage());
            fail(e.getMessage());
        }
    }

    @Test
    public void addUser() {
        try {
            User user = usersDAO.read("best");
            assertNotNull(user);
            assertTrue(user.getId() != 0);
            assertEquals("cashier", user.getPosition());
            assertEquals("Maрія", user.getName());
            assertEquals("qwerty", user.getPassword());

            User user1 = usersDAO.read("ALEX");
            assertNotNull(user);
            assertTrue(user.getId() != 0);
            assertEquals("cashier", user1.getPosition());
            assertEquals("sasha", user1.getName());
            assertEquals("life", user1.getPassword());

        } catch (DAOException e) {
            log.error(e.getMessage());
            fail(e.getMessage());
        }
    }
    /*
    @Test
    void addUser() {
        try {
            List<User> usersBefore = usersDAO.getAllGenres();
            assertTrue(usersBefore.size() == 0);

            usersDAO.addNewIssue(testUser1);
            List<User> usersAfter = usersDAO.getAllGenres();

            assertTrue(testUser1.getId() != 0);
            sameUserFromDB = usersDAO.getById(testUser1.getId());

            assertNotNull(sameUserFromDB);
            assertEquals(testUser1, sameUserFromDB);
            assertEquals(1, usersAfter.size());
            assertEquals(testUser1, sameUserFromDB);

        } catch (DaoException e) {
            log.error(e.getMessage());
            fail(e.getMessage());
        }
    }

    @Test
    void addNullableUser() {
        Assertions.assertThrows(DaoException.class, () -> {
            usersDAO.addNewIssue(null);
        });
    }

    @Test
    void addUserWithNonNullKey() {
        Assertions.assertThrows(DaoException.class, () -> {
            testUser1.setId(1);
            usersDAO.addNewIssue(testUser1);
        });
    }

    @Test
    void addUserWithSameLogin() {
        Assertions.assertThrows(DaoException.class, () -> {
            usersDAO.addNewIssue(testUser1);
            usersDAO.addNewIssue(new User(testUser1.getLogin(), "pass"));
        });
    }

    @Test
    void addSameUserTwice() {
        Assertions.assertThrows(DaoException.class, () -> {
            usersDAO.addNewIssue(testUser1);
            usersDAO.addNewIssue(testUser1);
        });
    }

    @Test
    void getByValidPK() {
        try {
            usersDAO.addNewIssue(testUser1);
            assertNotNull(testUser1.getId());

            sameUserFromDB = usersDAO.getById(testUser1.getId());

            assertNotNull(sameUserFromDB);
            assertEquals(testUser1, sameUserFromDB);
        } catch (DaoException e) {
            log.error(e.getMessage());
            fail(e.getMessage());
        }
    }

    @Test
    void getByInvalidPK() {
        Assertions.assertThrows(DaoException.class, () -> {
            User result = usersDAO.getById(11);
        });
    }

    @Test
    void getByNullableKey() {
        Assertions.assertThrows(DaoException.class, () -> {
            User result = usersDAO.getById(null);
        });
    }

    @Test
    void updateExistingUser() {
        try {
            genId = usersDAO.addNewIssue(testUser1);
            assertTrue(genId != 0);

            sameUserFromDB = usersDAO.getById(genId);
            assertEquals(testUser1, sameUserFromDB);

            String newPass = "11";
            sameUserFromDB.setPassword(newPass);
            usersDAO.update(sameUserFromDB);

            sameUserFromDB = usersDAO.getById(genId);
            assertNotEquals(sameUserFromDB, testUser1);
            assertEquals(newPass, sameUserFromDB.getPassword());
        } catch (DaoException e) {
            log.error(e.getMessage());
            fail(e.getMessage());
        }
    }

    @Test
    void updateNonExistingUser() {
        Assertions.assertThrows(DaoException.class, () -> {
            usersDAO.update(nonExistingUser);
        });
    }

    @Test
    void updateNullableUser() {
        Assertions.assertThrows(DaoException.class, () -> {
            usersDAO.update(null);
        });
    }

    @Test
    void deleteExistingUser() {
        try {
            genId = usersDAO.addNewIssue(testUser1);
            assertTrue(genId != 0);

            int expectedTableSize = 1;
            int resultTableSize = usersDAO.getAllGenres().size();
            assertEquals(expectedTableSize, resultTableSize);

            sameUserFromDB = usersDAO.getById(genId);
            assertEquals(testUser1, sameUserFromDB);

            usersDAO.deletePeriodicalById(sameUserFromDB.getId());

            expectedTableSize = 0;
            resultTableSize = usersDAO.getAllGenres().size();
            assertEquals(expectedTableSize, resultTableSize);
        } catch (DaoException e) {
            log.error(e.getMessage());
            fail(e.getMessage());
        }
    }

    @Test
    void deleteNonExistingUser() {
        Assertions.assertThrows(DaoException.class, () -> {
            usersDAO.deletePeriodicalById(nonExistingUser.getId());
        });
    }

    @Test
    void deleteNullableUser() {
        Assertions.assertThrows(DaoException.class, () -> {
            usersDAO.deletePeriodicalById(null);
        });
    }

    @Test
    void deleteNotIdentifiedUser() {
        Assertions.assertThrows(DaoException.class, () -> {
            usersDAO.deletePeriodicalById(testUser2.getId());
        });
    }

    @Test
    void getExistingUserByLogin() {
        try {
            genId = usersDAO.addNewIssue(testUser1);
            assertTrue(genId != 0);

            sameUserFromDB = usersDAO.getByLogin(testUser1.getLogin());
            assertNotNull(sameUserFromDB);
            assertEquals(testUser1, sameUserFromDB);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getNonExistingUserByLogin() {
        try {
            int tableSize = usersDAO.getAllGenres().size();
            assertTrue(tableSize == 0);

            Assertions.assertThrows(DaoException.class, () -> {
                usersDAO.getByLogin(nonExistingUser.getLogin());
            });
        } catch (DaoException e) {
            log.error(e.getMessage());
            fail(e.getMessage());
        }
    }

    @Test
    void getUserByNullableLogin() {
        Assertions.assertThrows(DaoException.class, () -> {
            usersDAO.getByLogin(null);
        });
    }*/
}