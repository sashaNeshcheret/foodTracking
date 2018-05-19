package ua.simpleproject.comand;

import org.apache.log4j.Logger;
import ua.simpleproject.DTO.ProductCurrentCheck;
import ua.simpleproject.command.LoginCommand;
import ua.simpleproject.services.AddProductLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddProductCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        List<ProductCurrentCheck> productCurrentCheckList = new ArrayList<>();
        String page = null;
//  взяти назву або код товару і їх кількість з запиту
        String name = (String) request.getParameter("name");
        String codeStr = (String) request.getParameter("code");
        String numberStr = (String) request.getParameter("number");

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if (Objects.isNull(login)){
            page = "/jsp//login.jsp";
            logger.error("");
            return page;
        }
        try{
            productCurrentCheckList = AddProductLogic.addProduct(name, codeStr, numberStr, login);
            page = "/jsp/openedCheck.jsp";
            request.setAttribute("list", productCurrentCheckList);
            return page;
        }catch(Exception e){
            page = "/jsp//error/error.jsp";
            logger.error("", e);
            return page;
        }
    }
}