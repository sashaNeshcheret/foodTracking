package ua.trackingFood.command;

import org.apache.log4j.Logger;
import ua.trackingFood.command.Command;
import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.ServiceFactory;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_ERROR_MESSAGE_LOGIN;
import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_ID;
import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_PAGE_NUM;
import static ua.trackingFood.utils.resourceHolders.MessagesHolder.NUM_PER_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.CHOOSE_CATEGORY_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.LOGIN_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.PRODUCTS_LIST_PAGE;

public class ChooseCategoryCommand implements Command {
    private static final GeneralService generalService = ServiceFactory.getServiceFactory().getGeneralService();
    private static final Logger LOGGER = Logger.getLogger(ChooseCategoryCommand.class.getSimpleName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(ATTR_ID);
        int from = 0;
        if(!verify(request, id)){
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
        int categoryId = 0;
        try{
            categoryId = Integer.parseInt(id);
        }catch (NumberFormatException e){
            List<CategoryProducts> categoryList = generalService.readCategories();
            request.setAttribute(ATTR_PAGE_NUM, from);
            request.setAttribute("message", "Internal mistake was happen");
            request.setAttribute("list", categoryList);
            request.getRequestDispatcher(CHOOSE_CATEGORY_PAGE).forward(request,response);
        }
        List<Product> productsList = generalService.getProductsList(categoryId, from*NUM_PER_PAGE, NUM_PER_PAGE);
        CategoryProducts categoryProducts = generalService.readCategory(categoryId);
        request.setAttribute(ATTR_PAGE_NUM, from);
        request.setAttribute("categoryProducts", categoryProducts);
        request.setAttribute("productsList", productsList);
        LOGGER.info("method execute run");
        request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
    }

    private boolean verify(HttpServletRequest request, String id){
        if(!EnterDataValidator.isValidNumber(id)){
            request.setAttribute(ATTR_ERROR_MESSAGE_LOGIN,"Sorry, but category is not correct");
            return false;
        }
        return true;
    }
}
