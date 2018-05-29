package ua.trackingFood.command;

import org.apache.log4j.Logger;
import ua.trackingFood.command.Command;
import ua.trackingFood.command.LoginCommand;
import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.entity.UserResult;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.service.*;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;
import static ua.trackingFood.utils.resourceHolders.MessagesHolder.NUM_PER_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.PERSONAL_LIST_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.PRODUCTS_LIST_PAGE;

public class ChooseEatenProductsCommand implements Command {
    private static final GeneralService generalService = ServiceFactory.getServiceFactory().getGeneralService();
    private static final AddEatenProductsService addEatenProductsService = ServiceFactory.getServiceFactory().getAddEatenProductsService();
    private static final ShowEatenProductsService showEatenProductsService = ServiceFactory.getServiceFactory().getShowEatenProductsService();
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class.getSimpleName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, BigDecimal> map = new HashMap<>();
        int userId = (int)request.getSession().getAttribute(ATTR_USER_ID);
        String categoryId = request.getParameter(ATTR_CATEGORY_ID);
        String nameSubmit1 = request.getParameter(ATTR_PREVIOUS);
        String nameSubmit2 = request.getParameter(ATTR_NEXT);
        String nameSubmit3 = request.getParameter(ATTR_PREVIOUS_PROD);
        String nameSubmit4 = request.getParameter(ATTR_NEXT_PROD);
        String nameSubmit5 = request.getParameter(ATTR_ADD);
        String[] arrayId = request.getParameterValues(ATTR_ID);
        String[] arrayNumber = request.getParameterValues(ATTR_NUMBER);
        String pageNumberStr = request.getParameter(ATTR_PAGE_NUM);
        boolean lastPageProd = false;
        boolean lastCategory = false;
        if(!verify(request, categoryId, pageNumberStr)){
            List<CategoryProducts> categoryList = generalService.readCategories();
            request.setAttribute("message", "Internal mistake was happen");
            request.setAttribute("list", categoryList);
            request.getRequestDispatcher("/WEB-INF/jsp/chooseCategory.jsp").forward(request,response);
            return;
        }
        int from = Integer.parseInt(pageNumberStr);
        int category = Integer.parseInt(categoryId);
        try{
            map = createMap(arrayId, arrayNumber);
        }catch(NumberFormatException e){
            request.setAttribute("errorMessage", "entered data is not correct, please try again");
            List<Product> productsList = generalService.getProductsList(Integer.parseInt(categoryId), 0, NUM_PER_PAGE);
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("productsList", productsList);
            request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
            return;
        }
        for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
            Product product = showEatenProductsService.getProduct(Integer.parseInt(entry.getKey()));
            addEatenProductsService.createEatenProducts(product, entry.getValue(), userId);
        }
        List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(userId);
        if(Objects.equals(ATTR_ADD, nameSubmit5) ||
                Objects.equals("look eaten products", nameSubmit5)){
            UserResult userResult = generalService.readUserResultInfo(userId);
            EatenProducts eatenProduct = showEatenProductsService.getResultEatenProduct(eatenProductsList);
            String message = generalService.consumptionAnalysis(eatenProduct, userResult);
            request.setAttribute("message", message);
            request.setAttribute("eatenProduct", eatenProduct);
            request.setAttribute("eatenProductsList", eatenProductsList);
            request.getRequestDispatcher(PERSONAL_LIST_PAGE).forward(request,response);
            return;
        }
        try {
            int oldCategory = category;
            category = generalService.paginationCategory(oldCategory, nameSubmit1, nameSubmit2);
            if(category == -1){
                category = ++oldCategory;
                lastCategory = true;
            }
            int pageNum = from;
            from = generalService.paginationProducts(pageNum, nameSubmit3, nameSubmit4);
            if(pageNum==from){
                from=0;
            }
        } catch (DAOException e) {
            LOGGER.error("Method pagination throw exception", e);
        }
        List<Product> productsList = generalService.getProductsList(category, (from)*NUM_PER_PAGE, NUM_PER_PAGE);
        if(productsList != null){
            if(productsList.size()<NUM_PER_PAGE && productsList.size()!=0){
                --from;
                lastPageProd=true;
            }
        }
        CategoryProducts categoryProducts = generalService.readCategory(category);
        request.setAttribute(ATTR_PAGE_NUM, from);
        request.setAttribute("lastCategory", lastCategory);
        request.setAttribute("lastPageProd", lastPageProd);
        request.setAttribute("categoryProducts", categoryProducts);
        request.setAttribute("productsList", productsList);
        request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
    }



    private Map<String, BigDecimal> createMap(String[] arrayId, String[] arrayNumberStr) throws NumberFormatException{
        Map<String, BigDecimal> map = new HashMap<>();
        BigDecimal number = null;
        if(Objects.isNull(arrayId) || Objects.isNull(arrayNumberStr)){
            return map;
        }
        if(arrayId.length == arrayNumberStr.length){
            for(int i=0; i<arrayId.length; i++){
                number = new BigDecimal(arrayNumberStr[i]);
                if(number.compareTo(new BigDecimal("0")) == 1){
                    map.put(arrayId[i], new BigDecimal(arrayNumberStr[i]));
                }else if(number.compareTo(new BigDecimal("0")) == -1){
                    throw new NumberFormatException();
                }
            }
        }
        return map;
    }
    private boolean verify(HttpServletRequest request, String id, String page){
        if(Objects.isNull(id)) {
            request.setAttribute("errorMessageLogin","You didn't enter category id");
            return false;
        }
        if(!EnterDataValidator.isValidPositiveNumber(id)){
            request.setAttribute("errorMessageLogin","id isn't correct");
            return false;
        }
        if(!EnterDataValidator.isValidPositiveNumber(page)){
            request.setAttribute("errorMessageLogin","page number isn't correct");
            return false;
        }
        return true;
    }
}
