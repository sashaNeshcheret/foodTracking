package ua.simpleproject.services;

import ua.simpleproject.dao.DAOCheckReports;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.dao.DAOUsers;
import ua.simpleproject.entity.CheckReports;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OutXLogic {
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOCheckReports daoCheckReports = daoFactory.getDaoCheckReports();
    private DAOUsers daoUsers = daoFactory.getDaoUsers();

    public List<CheckReports> outXReport(String login) throws DAOException {
        List<CheckReports> list = new ArrayList<>();
        List<CheckReports> list1 = new ArrayList<>();
        //int date = GregorianCalendar.DATE;
        LocalDate now = LocalDate.now(); // current date and time
        //LocalDateTime midnight = now.toLocalDate().atStartOfDay();
        User user = daoUsers.read(login);

        list1 = outXReport(now, user.getId());
        return list1;
    }

    public List<CheckReports> outXReport(LocalDate midnight, int userId){
        List<CheckReports> checkReportsList = null;
        try {
            checkReportsList = daoCheckReports.read(midnight, userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return checkReportsList;
    }
}
