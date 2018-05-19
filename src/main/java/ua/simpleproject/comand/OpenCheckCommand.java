package ua.simpleproject.comand;

import ua.simpleproject.services.OpenCheckLogic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class OpenCheckCommand implements ActionCommand {
    private OpenCheckLogic openCheckLogic = new OpenCheckLogic();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if(Objects.isNull(login)){
            request.setAttribute("errorLogin", "Sorry, but you do not have the right to open a check");
            page = "/jsp//login.jsp";
            return page;
        }

        if(openCheckLogic.openCheck(login)){
            page = "/jsp/openCheck.jsp";
            return page;
        } else {
            request.setAttribute("openCheck", "Sorry, but for technical reasons you can not open a check");
            page = "/jsp/error/error.jsp";
        }
        return page;
    }
}
