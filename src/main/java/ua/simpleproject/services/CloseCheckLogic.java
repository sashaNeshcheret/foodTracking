package ua.simpleproject.services;

import ua.simpleproject.dao.DAOCheckReports;
import ua.simpleproject.dao.DAOCurrentCheck;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.CheckReports;
import ua.simpleproject.entity.CurrentCheck;
import ua.simpleproject.exception.DAOException;

import java.math.BigDecimal;
import java.util.List;

public class CloseCheckLogic {
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOCurrentCheck daoCurrentCheck = daoFactory.getDaoCurrentCheck();
    private DAOCheckReports daoCheckReports = daoFactory.getDaoCheckReports();

    public CheckReports closeCheck(String login) throws DAOException {
        BigDecimal priceCheck = new BigDecimal(0);
        List<CurrentCheck> list = daoCurrentCheck.read(login);
        int userId = 0;
        for (CurrentCheck currentCheck : list){
            priceCheck = currentCheck.getResultPrice().add(priceCheck);
            userId = currentCheck.getUserId();
        }
        CheckReports checkReports = new CheckReports(userId, list.size(), priceCheck);
        daoCheckReports.create(checkReports);
        return checkReports;
    }
}
