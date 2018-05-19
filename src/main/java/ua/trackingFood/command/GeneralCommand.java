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

public class GeneralCommand implements Command {
    private LoginService loginService = new LoginService();
    private GeneralService generalService = new GeneralService();
    private ShowEatenProductsService showEatenProductsService = new ShowEatenProductsService();
    //private Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        User user = loginService.getUserByLogin(login);
        session.setAttribute("userId",user.getId());
        List<CategoryProducts> list = generalService.readCategory();
        UserResult userResult = generalService.readUserResultInfo(user.getId());
        UserParam userParam = generalService.readUserParamInfo(user.getId());
        List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(user.getId());
        EatenProducts eatenProduct = showEatenProductsService.getResultEatenProduct(eatenProductsList);
        EatenProducts availableBalance = generalService.availableBalance(userResult, eatenProduct);
        request.setAttribute("user", user);
        request.setAttribute("userResult", userResult);
        request.setAttribute("userParam", userParam);
        request.setAttribute("eatenProduct", eatenProduct);
        request.setAttribute("availableBalance", availableBalance);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/jsp/general.jsp").forward(request,response);
    }

    private boolean verify(HttpServletRequest request, String login, String password){
        if(Objects.isNull(login) || Objects.isNull(password)) {
            request.setAttribute("errorMessageLogin","You didn't enter login or password");
            return false;
        }
        if(!EnterDataValidator.isValidLogin(login)){
            request.setAttribute("errorMessageLogin","login incorrect");
            return false;
        }
        if(!EnterDataValidator.isValidPassword(password)){
            request.setAttribute("errorMessageLogin","password incorrect");
            return false;
        }
        return true;
    }
}
