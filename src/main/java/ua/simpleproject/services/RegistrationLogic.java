package ua.simpleproject.services;

import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionPool;

public class RegistrationLogic {
    public static boolean checkLoginForFree(String login) {
        User user = null;
        if(login == null){
            return false;
        }
        try {
            user = DAOFactory.getDaoFactory().getDaoUsers().read(login);
        } catch (DAOException e) {
            e.printStackTrace();// log4j
        }
        if(user != null){
            if(login.equals(user.getLogin())){
                return false;
            }
        }
        return true;
    }
    public static boolean registration(User user){
        DAOFactory daoFactory = DAOFactory.getDaoFactory();
        try{
            daoFactory.getDaoUsers().create(user);
        }catch(DAOException e){
            return false;
        }
        return true;
    }
}