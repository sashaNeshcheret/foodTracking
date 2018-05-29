package ua.trackingFood.command;


import org.apache.log4j.Logger;
import ua.trackingFood.command.Command;
import ua.trackingFood.entity.*;
import ua.trackingFood.exception.ServiceException;
import ua.trackingFood.service.*;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;
import static ua.trackingFood.utils.resourceHolders.MessagesHolder.NUM_PER_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.PRODUCTS_LIST_PAGE;

public class AddNewProductCommand implements Command {
    private static final GeneralService generalService = ServiceFactory.getServiceFactory().getGeneralService();
    private static final AddNewProductService addNewProductService = ServiceFactory.getServiceFactory().getAddNewProductService();
    private static final Logger LOGGER = Logger.getLogger(AddNewProductCommand.class.getSimpleName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(ATTR_CATEGORY_ID);
        String name = request.getParameter(ATTR_PRODUCT_NAME);
        String energyValue = request.getParameter(ATTR_ENERGY_VALUE);
        String proteins = request.getParameter(ATTR_PROTEINS);
        String carbohydrates = request.getParameter(ATTR_CARBOHYDRATES);
        String fats = request.getParameter(ATTR_FATS);

        if((verifyParam(request, id, energyValue, proteins, carbohydrates, fats)) &&
                verifyName(request, name)){
            try {
                addNewProductService.createProduct(id, name, energyValue, proteins, carbohydrates, fats);
            }
            catch (ServiceException e){
                request.setAttribute(ATTR_ERROR_MESSAGE_ADD, "Error enter parameter");
                LOGGER.error("Error enter parameter");
            }
        }
        int categoryId = Integer.parseInt(id);
        List<Product> productsList = generalService.getProductsList(categoryId, 0, NUM_PER_PAGE);
        CategoryProducts categoryProducts = generalService.readCategory(categoryId);
        request.setAttribute(ATTR_PAGE_NUM, 0);
        request.setAttribute("categoryProducts", categoryProducts);
        request.setAttribute("productsList", productsList);
        request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
    }
    private boolean verifyName(HttpServletRequest request, String name){
        if(!EnterDataValidator.isValidName(name)){
            request.setAttribute(ATTR_ERROR_MESSAGE_ADD,"Sorry, but name is not correct");
            return false;
        }
        return true;
    }
    private boolean verifyParam(HttpServletRequest request, String ... params){
        BigDecimal value = null;
        for(String param : params){
            if(!EnterDataValidator.isValidPositiveNumber(param)) {
                request.setAttribute(ATTR_ERROR_MESSAGE_ADD, "Sorry, but parameters are not correct");
                return false;
            }
        }
        return true;
    }
}
