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

public class GoToGeneralCommand implements Command {
    private Logger logger = Logger.getLogger("GoToChangeParamCommand.class");
    private GeneralService generalService = new GeneralService();
    private LoginService loginService = new LoginService();
    private ShowEatenProductsService showEatenProductsService = new ShowEatenProductsService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        User user = loginService.getUserByLogin(login);
        List<CategoryProducts> categoryList = generalService.readCategory();
        UserResult userResult = generalService.readUserResultInfo(user.getId());
        UserParam userParam = generalService.readUserParamInfo(user.getId());
        List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(user.getId());
        EatenProducts eatenProduct = showEatenProductsService.getResultEatenProduct(eatenProductsList);
        EatenProducts availableBalance = generalService.availableBalance(userResult, eatenProduct);
//String message = generalService.consumptionAnalysis(eatenProduct, userResult);
//request.setAttribute("message", message);
        request.setAttribute("userResult", userResult);
        request.setAttribute("userResult", userResult);
        request.setAttribute("userParam", userParam);
        request.setAttribute("eatenProduct", eatenProduct);
        request.setAttribute("availableBalance", availableBalance);
        request.setAttribute("list", categoryList);
        request.getRequestDispatcher("/WEB-INF/jsp/general.jsp").forward(request,response);
    }
}
