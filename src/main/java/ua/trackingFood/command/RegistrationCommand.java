package ua.trackingFood.command;

import ua.trackingFood.entity.UserContact;
import ua.trackingFood.exception.RegistrationException;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.RegistrationService;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.REGISTRATION_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.REGISTRATION_PARAM_PAGE;

public class RegistrationCommand implements Command {
    private Logger logger = Logger.getLogger("RegistrationCommand.class");
private LoginService loginService = new LoginService();//check
    private RegistrationService registerService =  new RegistrationService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(ATTR_LOGIN);
        String password = request.getParameter(ATTR_PASSWORD);
        String name = request.getParameter(ATTR_NAME);
        String surName = request.getParameter(ATTR_SURNAME);
        String mailAddress = request.getParameter(ATTR_EMAIL);

        if(!verify(request, login, password, name, surName, mailAddress)){
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
            return;
        }
//UserContact userContact = loginService.getUserByLogin(login);
//request.setAttribute("userName", userContact.getName());
//request.getRequestDispatcher("/general.jsp").forward(request,response);
        UserContact userContact = new UserContact(login, password, name, surName, mailAddress);

        try {
            registerService.register(userContact);
            request.setAttribute(ATTR_LOGIN, login);
            request.getRequestDispatcher(REGISTRATION_PARAM_PAGE).forward(request,response);
        } catch (RegistrationException e){
            request.setAttribute(ATTR_ERROR_MESSAGE, "Internal mistake during registration");
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request,response);
        }
    }

    private boolean verify(HttpServletRequest request, String login, String password, String name,
                           String surname, String mailAddress){
        if(Objects.isNull(login) || Objects.isNull(password) || Objects.isNull(name) ||
                Objects.isNull(surname) || Objects.isNull(mailAddress)) {
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN,"You didn't enter parameter");
            return false;
        }
        if(!EnterDataValidator.isValidLogin(login)){
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN,"login incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidPassword(password)){
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN,"password incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidName(name)|| !EnterDataValidator.isValidName(surname)){
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN,"name/surname incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidEmail(mailAddress)){
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN,"email incorrect");
            return false;
        }
        return true;
    }
}
