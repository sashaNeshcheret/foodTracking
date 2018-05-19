package ua.simpleproject.comand;

import ua.simpleproject.entity.CheckReports;
import ua.simpleproject.DTO.ProductCurrentCheck;
import ua.simpleproject.services.AddProductLogic;
import ua.simpleproject.services.CloseCheckLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CloseCheckCommand implements ActionCommand {
    private CloseCheckLogic closeCheckLogic = new CloseCheckLogic();


    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        try{
            CheckReports checkReports = closeCheckLogic.closeCheck(login);

            List<ProductCurrentCheck> productCurrentCheckList = AddProductLogic.getCurrentCheck(login);
            page = "/jsp/closeCheck.jsp";

            request.setAttribute("checkReports", checkReports);
            request.setAttribute("list", productCurrentCheckList);
            return page;
        }catch(Exception e){
            page = "/jsp//error/error.jsp";
            return page;
        }
    }
}