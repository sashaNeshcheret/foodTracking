package ua.simpleproject.comand;

import org.apache.log4j.Logger;
import ua.simpleproject.DTO.ProductCurrentCheck;
import ua.simpleproject.command.LoginCommand;
import ua.simpleproject.entity.CheckReports;
import ua.simpleproject.services.AddProductLogic;
import ua.simpleproject.services.OutXLogic;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OutXCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(OutXCommand.class);
    private OutXLogic outXReport = new OutXLogic();

    @Override
    public String execute(HttpServletRequest request) {
        List<CheckReports> checkReportsList = new ArrayList<>();
        String page = null;

        Locale locale = request.getLocale();
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        if (Objects.isNull(login)){
            page = "/jsp//login.jsp";
            logger.error("");
            return page;
        }
        try{
            checkReportsList = outXReport.outXReport(login);

            page = "/jsp/XReport.jsp";
            request.setAttribute("checkReportsList", checkReportsList);
            return page;
        }catch(Exception e){
            page = "/jsp//error/error.jsp";
            logger.error("", e);
            return page;
        }
    }
}
