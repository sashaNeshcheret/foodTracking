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

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_LOGIN;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.CHOOSE_CATEGORY_PAGE;

public class GoToChooseCategoryCommand implements Command {
    private Logger logger = Logger.getLogger("GoToChangeParamCommand.class");
    private GeneralService generalService = new GeneralService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ATTR_LOGIN);
        //String category = request.getParameter("categoryId");
        //int categoryId = Integer.parseInt(category);
        //UserContact userContact = loginService.getUserByLogin(login);
        //UserParam userParam = generalService.readUserParamInfo(userContact.getId());
        List<CategoryProducts> categoryList = generalService.readCategories();
        //List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(userContact.getId());
        //EatenProducts eatenProduct = showEatenProductsService.getResultEatenProduct(eatenProductsList);
       // CategoryProducts categoryProducts = generalService.readCategory(categoryId);
        //request.setAttribute("categoryProducts", categoryProducts);
        //request.setAttribute("eatenProduct", eatenProduct);
        //request.setAttribute("eatenProductsList", eatenProductsList);
        request.setAttribute("list", categoryList);
        //request.setAttribute("userContact", userContact);
        //request.setAttribute("userParam", userParam);
        request.getRequestDispatcher(CHOOSE_CATEGORY_PAGE).forward(request, response);
    }
}