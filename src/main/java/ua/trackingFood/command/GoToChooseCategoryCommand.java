package ua.trackingFood.command;

import ua.trackingFood.entity.*;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.ShowEatenProductsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class GoToChooseCategoryCommand implements Command {
    private Logger logger = Logger.getLogger("GoToChangeParamCommand.class");
    private GeneralService generalService = new GeneralService();
    private LoginService loginService = new LoginService();
    private ShowEatenProductsService showEatenProductsService = new ShowEatenProductsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        String categoryId = request.getParameter("categoryId");
        User user = loginService.getUserByLogin(login);
        //UserParam userParam = generalService.readUserParamInfo(user.getId());
        List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(user.getId());
        List<CategoryProducts> categoryList = generalService.readCategory();

        request.setAttribute("eatenProductsList", eatenProductsList);
        request.setAttribute("list", categoryList);
        request.setAttribute("user", user);
        request.setAttribute("categoryId", categoryId);
        //request.setAttribute("userParam", userParam);
        request.getRequestDispatcher("/WEB-INF/jsp/addProducts.jsp").forward(request, response);
    }
}