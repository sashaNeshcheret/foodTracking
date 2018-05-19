package ua.trackingFood.command;

import ua.trackingFood.entity.User;
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

public class RegistrationParamCommand implements Command {
    private Logger logger = Logger.getLogger("RegistrationParamCommand.class");
    private LoginService loginService = new LoginService();//check
    private RegistrationService registrationService = new RegistrationService();//check

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginParam = request.getParameter("login");
        String sex = request.getParameter("sex");
        String lifeStyle = request.getParameter("lifeStyle");
        String age = request.getParameter("age");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        String expectedResult = request.getParameter("expectedResult");

        if(!verify(request, sex, lifeStyle, age, height, weight, expectedResult)){
            request.getRequestDispatcher("/WEB-INF/jsp/registrationParam.jsp").forward(request, response);
        }

        String loginSession = (String) request.getSession().getAttribute("login");
        User user = null;
        boolean check = false;
        if (!Objects.isNull(loginSession)){
            user = loginService.getUserByLogin(loginSession);
            check = true;
        }else{
            user = loginService.getUserByLogin(loginParam);
            check = false;
        }
        UserParam userParam = new UserParam(user.getId(), sex,
                Integer.parseInt(lifeStyle), Integer.parseInt(age),
                new BigDecimal(height), new BigDecimal(weight), expectedResult);
        try {
            registrationService.registerStep2(userParam);
            if(check){
                request.getRequestDispatcher("/WEB-INF/jsp/general.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        } catch (RegistrationException e) {
            request.setAttribute("errorMessage", "Internal mistake during registration");
            request.getRequestDispatcher("/WEB-INF/jsp/registrationParam.jsp").forward(request,response);
        }
    }

    private boolean verify(HttpServletRequest request, String sex,String lifeStyle,
                           String age,String height,String weight,String expectedResult){
        if     (Objects.isNull(sex) || Objects.isNull(lifeStyle)||
                Objects.isNull(age) || Objects.isNull(height)||
                Objects.isNull(weight) || Objects.isNull(expectedResult)) {
            request.setAttribute("errorMessageParam","You didn't enter one or more parameters");
            return false;
        }
        if(!EnterDataValidator.isValidSex(sex)){
            request.setAttribute("errorMessageParam","sex incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidLifeStyle(lifeStyle)){
            request.setAttribute("errorMessageParam","life style incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidNumber(age)){
            request.setAttribute("errorMessageParam","age incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidNumber(height)){
            request.setAttribute("errorMessageParam","height incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidNumber(weight)){
            request.setAttribute("errorMessageParam","weight incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidExpectedResult(expectedResult)){
            request.setAttribute("errorMessageParam","expected result incorrect");
            return false;
        }
        return true;
    }
}
