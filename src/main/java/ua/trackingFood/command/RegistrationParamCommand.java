package ua.trackingFood.command;

import ua.trackingFood.entity.UserContact;
import ua.trackingFood.entity.UserParam;
import ua.trackingFood.exception.RegistrationException;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.RegistrationService;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.logging.Logger;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;
import static ua.trackingFood.utils.resourceHolders.AttributesHolder.GO_TO_GENERAL;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.LOGIN_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.REGISTRATION_PARAM_PAGE;

public class RegistrationParamCommand implements Command {
    private Logger logger = Logger.getLogger("RegistrationParamCommand.class");
    private LoginService loginService = new LoginService();//check
    private RegistrationService registrationService = new RegistrationService();//check

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginParam = request.getParameter(ATTR_LOGIN);
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
            if(check){
                FactoryCommand.getInstance().getCommand(GO_TO_GENERAL).execute(request, response);
                //request.getRequestDispatcher("/WEB-INF/jsp/general.jsp").forward(request,response);
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
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"sex incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidLifeStyle(lifeStyle)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"life style incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidNumber(age)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"age incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidNumber(height)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"height incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidNumber(weight)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"weight incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidExpectedResult(expectedResult)){
            request.setAttribute(ATTR_ERROR_MESSAGE_PARAM,"expected result incorrect");
            return false;
        }
        return true;
    }
}
