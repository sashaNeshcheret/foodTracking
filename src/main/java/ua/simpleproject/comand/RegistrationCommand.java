package ua.simpleproject.comand;


import ua.simpleproject.entity.User;
import ua.simpleproject.services.LoginLogic;
import ua.simpleproject.services.RegistrationLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegistrationCommand implements ActionCommand {

    private static final String PARAM_POSITION = "position";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_CHECK_WORD = "check word";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
// извлечение инфо из запроса
        String position = request.getParameter(PARAM_POSITION);
        String name = request.getParameter(PARAM_NAME);
        String login = request.getParameter(PARAM_LOGIN);
        String pass = request.getParameter(PARAM_PASSWORD);
        String checkWord = request.getParameter(PARAM_CHECK_WORD);

        if (RegistrationLogic.checkLoginForFree(login)) {
            User user = new User(position, name, "hgh",login, pass,checkWord);
            RegistrationLogic.registration(user);

            request.setAttribute("name", name);

            HttpSession session = request.getSession(true);
            String loginStr = (String) session.getAttribute("login");
            if(Objects.isNull(loginStr)){
                session.setAttribute("login",login);
                session.setAttribute("position", position);
                session.setMaxInactiveInterval(100);
            }else{
                session.setAttribute("login",loginStr);
                session.setAttribute("position", position);
            }
            if(position.equals("merchant")){
                page = "/jsp/productsList.jsp";
                return page;
            }else if(position.equals("senior_cashier") || position.equals("cashier")){
                page = "/jsp/general.jsp";
                return page;
            }
            page = "/jsp/error/error.jsp";
        } else {
            page = "/jsp/registration.jsp";
        }
        return page;
    }
}