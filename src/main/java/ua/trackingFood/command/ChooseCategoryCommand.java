package ua.trackingFood.command;

//import org.apache.log4j.Logger;
import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.entity.User;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ChooseCategoryCommand implements Command {
    private LoginService loginService = new LoginService();
    private GeneralService generalService = new GeneralService();
    //private Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        if(!verify(request, id)){
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        int categoryId = Integer.parseInt(id);
        List<Product> productsList = generalService.getProductsList(categoryId);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("productsList", productsList);
        request.getRequestDispatcher("/WEB-INF/jsp/productsList1.jsp").forward(request,response);
    }

    private boolean verify(HttpServletRequest request, String id){
        if(!EnterDataValidator.isValidNumber(id)){
            request.setAttribute("errorMessageLogin","Sorry, but category is not correct");
            return false;
        }
        return true;
    }
}
