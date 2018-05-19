package ua.trackingFood.command;

import ua.trackingFood.entity.User;
import ua.trackingFood.entity.UserParam;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class GoToChangeParamCommand implements Command {
    private Logger logger = Logger.getLogger("GoToChangeParamCommand.class");
    private GeneralService generalService = new GeneralService();
    private LoginService loginService = new LoginService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        User user = loginService.getUserByLogin(login);
        UserParam userParam = generalService.readUserParamInfo(user.getId());
        request.setAttribute("user", user);
        request.setAttribute("userParam", userParam);
        request.getRequestDispatcher("/WEB-INF/jsp/changeRegistrationParam.jsp").forward(request, response);
    }
}
