package ua.trackingFood.command;

import ua.trackingFood.command.AddNewCategoryCommand;
import ua.trackingFood.command.Command;
import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.UserContact;
import ua.trackingFood.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_CATEGORY_ID;
import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_PRODUCTS_ID;
import static ua.trackingFood.utils.resourceHolders.AttributesHolder.LOGIN;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.PERSONAL_LIST_PAGE;

public class DeleteEatenProductCommand implements Command {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AddNewCategoryCommand.class.getSimpleName());
    private static final GeneralService generalService = ServiceFactory.getServiceFactory().getGeneralService();
    private static final LoginService loginService = ServiceFactory.getServiceFactory().getLoginService();
    private static final ShowEatenProductsService showEatenProductsService = ServiceFactory.getServiceFactory().getShowEatenProductsService();
    private static final AddEatenProductsService addEatenProductsService = ServiceFactory.getServiceFactory().getAddEatenProductsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(LOGIN);
        String category = request.getParameter(ATTR_CATEGORY_ID);
        String product = request.getParameter(ATTR_PRODUCTS_ID);
        int id = 0;
        int categoryId = 0;
        try{
            id = Integer.parseInt(product);
            categoryId = Integer.parseInt(category);
        }catch (NumberFormatException e){
            LOGGER.error( "id of product to be deleted is not correct" );
        }
        UserContact userContact = loginService.getUserByLogin(login);
        addEatenProductsService.deleteProduct(id);
        List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(userContact.getId());
        List<CategoryProducts> categoryList = generalService.readCategories();
        CategoryProducts categoryProducts = generalService.readCategory(categoryId);
        request.setAttribute("categoryProducts", categoryProducts);
        EatenProducts eatenProduct = showEatenProductsService.getResultEatenProduct(eatenProductsList);
        request.setAttribute("eatenProduct", eatenProduct);
        request.setAttribute("eatenProductsList", eatenProductsList);
        request.setAttribute("list", categoryList);
        request.setAttribute("userContact", userContact);
        request.getRequestDispatcher(PERSONAL_LIST_PAGE).forward(request, response);
    }
}