package ua.trackingFood.command;

//import org.apache.log4j.Logger;
import ua.trackingFood.entity.*;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.ShowEatenProductsService;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.GENERAL_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.LOGIN_PAGE;

public class LoginCommand implements Command {
    private LoginService loginService = new LoginService();
    private GeneralService generalService = new GeneralService();
    private ShowEatenProductsService showEatenProductsService = new ShowEatenProductsService();
    //private Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter(ATTR_LOGIN);
        String password = request.getParameter(ATTR_PASSWORD);

        if(!verify(request, login, password)){
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }

        if(!loginService.checkLogin(login,password)){
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN, "Login or password incorrect");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
        session.setAttribute(ATTR_LOGIN,login);
        UserContact userContact = loginService.getUserByLogin(login);
        session.setAttribute(ATTR_USER_ID, userContact.getId());
        List<CategoryProducts> categoryList = generalService.readCategories();
        UserResult userResult = generalService.readUserResultInfo(userContact.getId());
        UserParam userParam = generalService.readUserParamInfo(userContact.getId());
        List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(userContact.getId());
        EatenProducts eatenProduct = showEatenProductsService.getResultEatenProduct(eatenProductsList);
        EatenProducts availableBalance = generalService.availableBalance(userResult, eatenProduct);
        String message = generalService.consumptionAnalysis(eatenProduct, userResult);
//String messageWarning = generalService.consumptionAnalysis(eatenProduct, userResult);
//String messageError = generalService.consumptionAnalysis(eatenProduct, userResult);
        request.setAttribute("message", message);
        request.setAttribute("userContact", userContact);
        request.setAttribute("userResult", userResult);
        request.setAttribute("userParam", userParam);
        request.setAttribute("eatenProduct", eatenProduct);
        request.setAttribute("availableBalance", availableBalance);
        request.setAttribute("list", categoryList);
        request.getRequestDispatcher(GENERAL_PAGE).forward(request,response);
    }

    private boolean verify(HttpServletRequest request, String login, String password){
        if(Objects.isNull(login) || Objects.isNull(password)) {
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN,"You didn't enter login or password");
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
        return true;
    }
}
