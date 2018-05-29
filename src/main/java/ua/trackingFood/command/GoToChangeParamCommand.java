package ua.trackingFood.command;

import ua.trackingFood.command.Command;
import ua.trackingFood.entity.UserContact;
import ua.trackingFood.entity.UserParam;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_LOGIN;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.CHANGE_PARAM_PAGE;

public class GoToChangeParamCommand implements Command {
    private static final GeneralService generalService = ServiceFactory.getServiceFactory().getGeneralService();
    private static final LoginService loginService = ServiceFactory.getServiceFactory().getLoginService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ATTR_LOGIN);

        UserContact userContact = loginService.getUserByLogin(login);
        UserParam userParam = generalService.readUserParamInfo(userContact.getId());
        request.setAttribute("userContact", userContact);
        request.setAttribute("userParam", userParam);
        request.getRequestDispatcher(CHANGE_PARAM_PAGE).forward(request, response);
    }
}
