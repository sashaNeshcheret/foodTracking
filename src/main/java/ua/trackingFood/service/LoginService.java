package ua.trackingFood.service;


import ua.trackingFood.dao.DAOUsers;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.User;
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
        User user = null;
        try {
            user = daoUsers.read(enterLogin);
            if(enterPass == null || user == null){
                return false;
            }
            if(enterLogin.equals(user.getLogin()) && enterPass.equals(user.getPassword())){
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
    public User getUserByLogin(String login){
        User user = null;
        try {
            user = DAOFactory.getDaoFactory().getDAOUsers().read(login);
            return user;
        } catch (DAOException e) {
            // log4j
        }
        return user;
    }
}
