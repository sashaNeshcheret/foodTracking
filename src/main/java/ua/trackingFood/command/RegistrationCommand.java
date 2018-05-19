package ua.trackingFood.command;

import ua.trackingFood.entity.User;
import ua.trackingFood.exception.RegistrationException;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.RegistrationService;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class RegistrationCommand implements Command {
    private Logger logger = Logger.getLogger("RegistrationCommand.class");
private LoginService loginService = new LoginService();//check
    private RegistrationService registerService =  new RegistrationService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surName = request.getParameter("surname");
        String mailAddress = request.getParameter("mailAddress");

        if(!verify(request, login, password, name, surName, mailAddress)){
            request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
            return;
        }
//User user = loginService.getUserByLogin(login);
//request.setAttribute("userName", user.getName());
//request.getRequestDispatcher("/general.jsp").forward(request,response);
        User user = new User(login, password, name, surName, mailAddress);

        try {
            registerService.register(user);
            request.setAttribute("login", login);
            request.getRequestDispatcher("/WEB-INF/jsp/registrationParam.jsp").forward(request,response);
        } catch (RegistrationException e){
            request.setAttribute("errorMessage", "Internal mistake during registration");
            request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request,response);
        }
    }

    private boolean verify(HttpServletRequest request, String login, String password, String name, String age, String calories){
        if(Objects.isNull(login) || Objects.isNull(password)) {
            request.setAttribute("errorMessageLogin","You didn't enter login or password");
            return false;
        }
        if(!EnterDataValidator.isValidLogin(login)){
            request.setAttribute("errorMessageLogin","login incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidPassword(password)){
            request.setAttribute("errorMessageLogin","password incorrect");
            return false;
        }
        return true;
    }
}
