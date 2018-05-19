package ua.simpleproject.comand;

import ua.simpleproject.DTO.ProductCurrentCheck;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.services.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class LoginCommand implements ActionCommand {
    private LoginLogic loginLogic = new LoginLogic();
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_NUMBER_PAGE = "numberPage";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
// извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        List<ProductCurrentCheck> list = null;


// проверка логина и пароля
        if (loginLogic.checkLogin(login, pass)) {
            HttpSession session = request.getSession(true);
            String userLogin = (String) session.getAttribute(PARAM_NAME_LOGIN);
            System.out.println(userLogin == null);

            if(Objects.isNull(userLogin)){
                session.setAttribute(PARAM_NAME_LOGIN, login);
                session.setMaxInactiveInterval(1000);
            }else{
                request.setAttribute(PARAM_NAME_LOGIN, userLogin);
            }
            User user = loginLogic.getUserByLogin(login);
            String position = null;
            if(user != null){
                position = user.getPosition();
            }
            if(position.equals("merchant")){
                String pageStock = null;
                if(request.getParameter(PARAM_NAME_NUMBER_PAGE).equals("")){
                    pageStock = "0";
                }
                else{
                    try {
                        pageStock = request.getParameter(PARAM_NAME_NUMBER_PAGE);
                        int pageInt = Integer.parseInt(pageStock);
                        list = loginLogic.getStock(pageInt);
                        request.setAttribute("list", list);
                        request.setAttribute(PARAM_NAME_NUMBER_PAGE, pageStock);
                        page = "/jsp/productsList.jsp";
                        return page;
                    } catch (DAOException e) {
                        page = "/jsp/error/error.jsp";
                        return page;
                    }
                }
            }else if(position.equals("senior_cashier") || position.equals("cashier")){
                page = "/jsp/general.jsp";
                return page;
            }
            page = "/jsp/error/error.jsp";
        } else {
            System.out.println("false");
            request.setAttribute("errorLoginPassMessage", "Your login or pass was entered wrong");//request.setAttribute("errorLoginPassMessage", "error");
System.out.println("next after false");
            page = "/jsp/login.jsp";
System.out.println(request.getAttribute("errorLoginPassMessage"));
        }
        return page;
    }
}
