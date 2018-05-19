package ua.simpleproject.services;

import ua.simpleproject.dao.DAOCurrentCheck;
import ua.simpleproject.dao.DAOUsers;
import ua.simpleproject.entity.User;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.exception.DAOException;

public class OpenCheckLogic {
    private DAOCurrentCheck daoCurrentCheck = DAOFactory.getDaoFactory().getDaoCurrentCheck();
    private DAOUsers daoUser = DAOFactory.getDaoFactory().getDaoUsers();

    /**
     * по логину отримуємо об"экт user
     * закриваємо попередній чек
     * і відкриваємо новий
     *
     * @param login - логин пользователя
     * @return - повертає true, якщо операція відкриття чеку була успішна
     */

    public boolean openCheck(String login) {
        User user = null;
        try {
            user = daoUser.read(login);
            daoCurrentCheck.delete(user.getId());
            return true;
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
