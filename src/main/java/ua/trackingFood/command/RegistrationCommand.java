package ua.trackingFood.command;

import org.apache.log4j.Logger;
import ua.trackingFood.command.Command;
import ua.trackingFood.entity.UserContact;
import ua.trackingFood.exception.RegistrationException;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.RegistrationService;
import ua.trackingFood.service.ServiceFactory;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.REGISTRATION_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.REGISTRATION_PARAM_PAGE;

public class RegistrationCommand implements Command {
    private static final  Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    private static final  RegistrationService registerService =  ServiceFactory.getServiceFactory().getRegistrationService();
    private static final LoginService loginService =  ServiceFactory.getServiceFactory().getLoginService();
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
        UserContact userContactExists = loginService.getUserByLogin(login);
        if(userContactExists != null){
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN,"Sorry, but login is exists ");
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
            return;
        }
        UserContact userContact = new UserContact(login, password, name, surName, mailAddress);

        try {
            registerService.register(userContact);
            request.setAttribute(ATTR_LOGIN, login);
            LOGGER.info("registration was successfully");
            request.getRequestDispatcher(REGISTRATION_PARAM_PAGE).forward(request,response);
        } catch (RegistrationException e){
            request.setAttribute(ATTR_ERROR_MESSAGE, "Internal mistake during registration");
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request,response);
        }
    }

    private boolean verify(HttpServletRequest request, String login, String password, String name,
                           String surname, String mailAddress){
        StringBuilder message = new StringBuilder();
        if(Objects.isNull(login) || Objects.isNull(password) || Objects.isNull(name) ||
                Objects.isNull(surname) || Objects.isNull(mailAddress)) {
            message.append("You didn't enter one or more parameters. ");
            return false;
        }
        if(!EnterDataValidator.isValidLogin(login)){
            message.append("Login isn't correct. ");
        }
        if(!EnterDataValidator.isValidPassword(password)){
            message.append("Password isn't correct. ");

        }
        if(!EnterDataValidator.isValidName(name)|| !EnterDataValidator.isValidName(surname)){
            message.append("Name/surname isn't correct. ");

        }
        if(!EnterDataValidator.isValidEmail(mailAddress)){
            message.append("Email isn't correct");
        }
        String attribute = message.toString();
        request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN, attribute);
        if(!attribute.isEmpty()){
            return false;
        }
        return true;
    }
}
