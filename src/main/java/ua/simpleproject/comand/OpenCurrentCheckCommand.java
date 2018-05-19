package ua.simpleproject.comand;

import ua.simpleproject.DTO.ProductCurrentCheck;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.services.AddProductLogic;
import ua.simpleproject.services.OpenCheckLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class OpenCurrentCheckCommand implements ActionCommand {
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

        List<ProductCurrentCheck> list = null;
        try {
            list = AddProductLogic.getCurrentCheck(login);
            request.setAttribute("list", list);
            page = "/jsp/openedCheck.jsp";
            return page;
        } catch (DAOException e) {
            request.setAttribute("error", "Помилка доступу до бази данних");
            page = "/jsp/error/error.jsp";
            return page;
        }

    }
}
