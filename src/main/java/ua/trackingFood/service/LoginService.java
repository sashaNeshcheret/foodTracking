package ua.trackingFood.service;


import ua.trackingFood.dao.DAOUsers;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.UserContact;
import ua.trackingFood.exception.DAOException;

import java.util.logging.Logger;

/**
 * Created by Нещерет on 01.05.2018.
 */
public class LoginService {
    private DAOUsers daoUsers = DAOFactory.getDaoFactory().getDAOUsers();
    private Logger logger = Logger.getLogger("LoginService.class");


    /**Check login and password in accordance of DB
     *
     * @param enterLogin
     * @param enterPass
     * @return
     */
    public boolean checkLogin(String enterLogin, String enterPass) {
        UserContact userContact = null;
        try {
            userContact = daoUsers.read(enterLogin);
            if(enterPass == null || userContact == null){
                return false;
            }
            if(enterLogin.equals(userContact.getLogin()) && enterPass.equals(userContact.getPassword())){
                return true;
            }
        } catch (DAOException e) {

        }
        return false;
    }

    /**Get object of user by its login
     *
     * @param login
     * @return
     */
    public UserContact getUserByLogin(String login){
        UserContact userContact = null;
        try {
            userContact = DAOFactory.getDaoFactory().getDAOUsers().read(login);
            return userContact;
        } catch (DAOException e) {
            // log4j
        }
        return userContact;
    }
}
