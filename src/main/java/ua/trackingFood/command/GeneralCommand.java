package ua.trackingFood.command;

import org.apache.log4j.Logger;
import ua.trackingFood.command.Command;
import ua.trackingFood.entity.*;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.ServiceFactory;
import ua.trackingFood.service.ShowEatenProductsService;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_LOGIN;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.GENERAL_PAGE;

public class GeneralCommand implements Command {
    private static final LoginService loginService = ServiceFactory.getServiceFactory().getLoginService();
    private static final GeneralService generalService = ServiceFactory.getServiceFactory().getGeneralService();
    private static final ShowEatenProductsService showEatenProductsService = ServiceFactory.getServiceFactory().getShowEatenProductsService();
    private static final Logger LOGGER = Logger.getLogger(GeneralCommand.class.getSimpleName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ATTR_LOGIN);
        UserContact userContact = loginService.getUserByLogin(login);
        session.setAttribute("userId", userContact.getId());
        UserResult userResult = generalService.readUserResultInfo(userContact.getId());
        UserParam userParam = generalService.readUserParamInfo(userContact.getId());
        List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(userContact.getId());
        EatenProducts eatenProduct = showEatenProductsService.getResultEatenProduct(eatenProductsList);
        EatenProducts availableBalance = generalService.availableBalance(userResult, eatenProduct);
        request.setAttribute("userContact", userContact);
        request.setAttribute("userResult", userResult);
        request.setAttribute("userParam", userParam);
        request.setAttribute("eatenProduct", eatenProduct);
        request.setAttribute("availableBalance", availableBalance);
        request.getRequestDispatcher(GENERAL_PAGE).forward(request,response);
    }

    private boolean verify(HttpServletRequest request, String login, String password){
        if(Objects.isNull(login) || Objects.isNull(password)) {
            request.setAttribute("ATTR_ERROR_MESSAGE_LOGIN","You didn't enter login or password");
            return false;
        }
        if(!EnterDataValidator.isValidLogin(login)){
            request.setAttribute("ATTR_ERROR_MESSAGE_LOGIN","login incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidPassword(password)){
            request.setAttribute("ATTR_ERROR_MESSAGE_LOGIN","password incorrect");
            return false;
        }
        return true;
    }
}
