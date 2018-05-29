package ua.trackingFood.command;

import org.apache.log4j.Logger;
import ua.trackingFood.command.Command;
import ua.trackingFood.command.FactoryCommand;
import ua.trackingFood.entity.UserContact;
import ua.trackingFood.entity.UserParam;
import ua.trackingFood.exception.RegistrationException;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.RegistrationService;
import ua.trackingFood.service.ServiceFactory;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;
import static ua.trackingFood.utils.resourceHolders.AttributesHolder.GO_TO_GENERAL;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.LOGIN_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.REGISTRATION_PARAM_PAGE;

public class RegistrationParamCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationParamCommand.class);
    private static final LoginService loginService = ServiceFactory.getServiceFactory().getLoginService();
    private static final RegistrationService registrationService = ServiceFactory.getServiceFactory().getRegistrationService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginParam = request.getParameter(ATTR_LOGIN);
        System.out.println(loginParam);
        String sex = request.getParameter(ATTR_SEX);
        String lifeStyle = request.getParameter(ATTR_LIFE_STYLE);
        String age = request.getParameter(ATTR_AGE);
        String height = request.getParameter(ATTR_HEIGHT);
        String weight = request.getParameter(ATTR_WEIGHT);
        String expectedResult = request.getParameter(ATTR_EXPECTED_RESULT);

        if(!verify(request, sex, lifeStyle, age, height, weight, expectedResult)){
            request.getRequestDispatcher(REGISTRATION_PARAM_PAGE).forward(request, response);
            return;
        }

        String loginSession = (String) request.getSession().getAttribute(ATTR_LOGIN);
        UserContact userContact = null;
        boolean check = false;
        if (!Objects.isNull(loginSession)){
            userContact = loginService.getUserByLogin(loginSession);
            check = true;
        }else{
            userContact = loginService.getUserByLogin(loginParam);
            check = false;
        }
        if (Objects.isNull(userContact)){
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN, "Sorry internal mistake");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }
        UserParam userParam = new UserParam(userContact.getId(), sex,
                Integer.parseInt(lifeStyle), Integer.parseInt(age),
                new BigDecimal(height), new BigDecimal(weight), expectedResult);
        try {
            registrationService.registerStep2(userParam, userContact);
            LOGGER.info("registration was successfully");
            if(check){
                FactoryCommand.getInstance().getCommand(GO_TO_GENERAL).execute(request, response);
            }else{
                request.getRequestDispatcher(LOGIN_PAGE).forward(request,response);
            }
        } catch (RegistrationException e) {
            request.setAttribute(ATTR_ERROR_MESSAGE, "Internal mistake during registration");
            request.getRequestDispatcher(REGISTRATION_PARAM_PAGE).forward(request,response);
        }
    }

    private boolean verify(HttpServletRequest request, String sex,String lifeStyle,
                           String age,String height,String weight,String expectedResult){
        if     (Objects.isNull(sex) || Objects.isNull(lifeStyle)||
                Objects.isNull(age) || Objects.isNull(height)||
                Objects.isNull(weight) || Objects.isNull(expectedResult)) {
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"You didn't enter one or more parameters");
            return false;
        }
        if(!EnterDataValidator.isValidSex(sex)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"sex isn't correct");
            return false;
        }
        if(!EnterDataValidator.isValidLifeStyle(lifeStyle)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"life style isn't correct");
            return false;
        }
        if(!EnterDataValidator.isValidAge(age)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"age isn't correct");
            return false;
        }
        if(!EnterDataValidator.isValidHeight(height)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"height isn't correct");
            return false;
        }
        if(!EnterDataValidator.isValidWeight(weight)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"weight isn't correct");
            return false;
        }
        if(!EnterDataValidator.isValidExpectedResult(expectedResult)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"expected result isn't correct");
            return false;
        }
        return true;
    }
}
