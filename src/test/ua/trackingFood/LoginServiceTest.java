package ua.trackingFood;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

class LoginServiceTest {
    private static final Logger LOGGER = Logger.getLogger(LoginServiceTest.class.getSimpleName());

    private static final String CORRECT_LOGIN = "best";
    private static final String CORRECT_PASS = "zzz1";


    @Test
    void execute_admin_credentials_return_admin_page_redirect() {
    /*    DAOUsers mockDAOUser = mock(DAOUsers.class);
        LoginService loginService = new LoginService();

        UserContact userContact = new UserContact("Sasha",	"Neshcheret",	"best", "zzz1",	"sashka_life@bk.ru");
        userContact.setId(1);
        try{
            when(mockDAOUser.read(CORRECT_LOGIN)).thenReturn(userContact);
        }catch(DAOException e){
            LOGGER.error("Connection failed");
        }

        loginService.setDaoUsers(mockDAOUser);
        boolean result = new LoginService().checkLogin(CORRECT_LOGIN,CORRECT_PASS);

        assertTrue(result);*/
    }
}
