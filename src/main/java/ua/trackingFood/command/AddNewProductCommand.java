package ua.trackingFood.command;

//import org.apache.log4j.Logger;
import ua.trackingFood.entity.*;
import ua.trackingFood.exception.ServiceException;
import ua.trackingFood.service.AddNewProductService;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.ShowEatenProductsService;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;
import static ua.trackingFood.utils.resourceHolders.MessagesHolder.NUM_PER_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.PRODUCTS_LIST_PAGE;

public class AddNewProductCommand implements Command {
    private GeneralService generalService = new GeneralService();
    private AddNewProductService addNewProductService = new AddNewProductService();
    //private Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(ATTR_CATEGORY_ID);
        String name = request.getParameter(ATTR_PRODUCT_NAME);
        String energyValue = request.getParameter(ATTR_ENERGY_VALUE);
        String proteins = request.getParameter(ATTR_PROTEINS);
        String dimension1 = request.getParameter(ATTR_DIMENSION_1);
        String carbohydrates = request.getParameter(ATTR_CARBOHYDRATES);
        String dimension2 = request.getParameter(ATTR_DIMENSION_2);
        String fats = request.getParameter(ATTR_FATS);
        String dimension3 = request.getParameter(ATTR_DIMENSION_3);
        String pageNumberStr = request.getParameter(ATTR_PAGE_NUM);
        int from = 0;
        int pageNumber = 0;

        if(verifyParam(request, id)){
            pageNumber = Integer.parseInt(pageNumberStr);
            from = pageNumber*NUM_PER_PAGE;
        }
        if((verifyParam(request, id, energyValue, proteins, carbohydrates, fats)) &&
                verifyName(request, name) && verify(request, dimension1, dimension2, dimension3)){
            try {
                addNewProductService.createProduct(id, name, energyValue, proteins, carbohydrates, fats);
            }
            catch (ServiceException e){
                request.setAttribute(ATTR_ERROR_MESSAGE_ADD, "Error enter parameter");
            }
        }
        int categoryId = Integer.parseInt(id);
        List<Product> productsList = generalService.getProductsList(categoryId, from, NUM_PER_PAGE);
        CategoryProducts categoryProducts = generalService.readCategory(categoryId);
        request.setAttribute(ATTR_PAGE_NUM, pageNumber);
        request.setAttribute("categoryProducts", categoryProducts);
        request.setAttribute("productsList", productsList);
        request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
    }

    private boolean verify(HttpServletRequest request, String...dimensions){
        for(String dimension : dimensions){
            if(!("kcal".equals(dimension) || "gramm".equals(dimension))){
                request.setAttribute(ATTR_ERROR_MESSAGE_ADD,"Sorry, but dimensions are not correct");
                return false;
            }
        }
        return true;
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
