package ua.trackingFood.command;

import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.entity.UserResult;
import ua.trackingFood.service.AddEatenProductsService;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.ShowEatenProductsService;
import ua.trackingFood.validation.EnterDataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;
import static ua.trackingFood.utils.resourceHolders.MessagesHolder.NUM_PER_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.PERSONAL_LIST_PAGE;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.PRODUCTS_LIST_PAGE;

public class AddAndShowEatenProductsCommand1 implements Command{
    private GeneralService generalService = new GeneralService();
    private AddEatenProductsService addEatenProductsService = new AddEatenProductsService();
    private ShowEatenProductsService showEatenProductsService = new ShowEatenProductsService();
    private Logger logger = Logger.getLogger("AddAndShowEatenProductsCommand.class");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /**        Map<String, BigDecimal> map = new HashMap<>();
        int userId = (int)request.getSession().getAttribute(ATTR_USER_ID);
        String categoryId = request.getParameter(ATTR_CATEGORY_ID);
        String nameSubmit1 = request.getParameter(ATTR_PREVIOUS);
        String nameSubmit2 = request.getParameter(ATTR_NEXT);
        String nameSubmit3 = request.getParameter(ATTR_PREVIOUS_PROD);
        String nameSubmit4 = request.getParameter(ATTR_NEXT_PROD);
        String[] arrayId = request.getParameterValues(ATTR_ID);
        String[] arrayNumber = request.getParameterValues(ATTR_NUMBER);
        String pageNumberStr = request.getParameter(ATTR_PAGE_NUM);
//TODO add checking categoryId and pageNumberStr
int from = Integer.parseInt(pageNumberStr);
int category = Integer.parseInt(categoryId);
        try{
            map = createMap(arrayId, arrayNumber);
        }catch(NumberFormatException e){
            request.setAttribute("errorMessage", "enter data is not correct, please try again");
            List<Product> productsList = generalService.getProductsList(Integer.parseInt(categoryId), from, NUM_PER_PAGE);
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("productsList", productsList);
            request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
        }
/*
        if(!verify(request, categoryId)){
            List<CategoryProducts> categoryList = generalService.readCategories();
            request.setAttribute("message", "Internal mistake was happen");
            request.setAttribute("list", categoryList);
            request.getRequestDispatcher("/WEB-INF/jsp/CHOOSE_CATEGORY.jsp").forward(request,response);
        }
*/
//map.forEach((k,v)->(k + v));
/*        EatenProducts eatenProducts = null;
        for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
            Product product = showEatenProductsService.getProduct(Integer.parseInt(entry.getKey()));
            addEatenProductsService.createEatenProducts(product, entry.getValue(), userId);
        }

        List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(userId);

        if(Objects.equals(ATTR_PREVIOUS, nameSubmit1)){
            List<Product> productsList = generalService.getProductsList(--category, from, NUM_PER_PAGE);
            CategoryProducts categoryProducts = generalService.readCategory(category);
            request.setAttribute("categoryProducts", categoryProducts);
            request.setAttribute("productsList", productsList);
            request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
        }
        else if(Objects.equals(ATTR_NEXT, nameSubmit2)){
            List<Product> productsList = generalService.getProductsList(++category, from, NUM_PER_PAGE);
            CategoryProducts categoryProducts = generalService.readCategory(category);
            if(Objects.isNull(categoryProducts)){
                categoryProducts = generalService.readCategory(category-1);
                request.setAttribute("categoryProducts", categoryProducts);
            }else{
                request.setAttribute("categoryProducts", categoryProducts);
                request.setAttribute("productsList", productsList);
            }
            request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
        }
        if(Objects.equals(ATTR_PREVIOUS_PROD, nameSubmit3)){
            List<Product> productsList = generalService.getProductsList(category, --from, NUM_PER_PAGE);
            CategoryProducts categoryProducts = generalService.readCategory(category);
            request.setAttribute("categoryProducts", categoryProducts);
            request.setAttribute("productsList", productsList);
            request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
        }
        else if(Objects.equals(ATTR_NEXT_PROD, nameSubmit4)){
            int category = Integer.parseInt(categoryId)+1;
            List<Product> productsList = generalService.getProductsList(category, from, NUM_PER_PAGE);
            CategoryProducts categoryProducts = generalService.readCategory(category);
            if(Objects.isNull(categoryProducts)){
                categoryProducts = generalService.readCategory(category-1);
                request.setAttribute("categoryProducts", categoryProducts);
            }else{
                request.setAttribute("categoryProducts", categoryProducts);
                request.setAttribute("productsList", productsList);
            }
            request.getRequestDispatcher(PRODUCTS_LIST_PAGE).forward(request,response);
        }
        else{
            UserResult userResult = generalService.readUserResultInfo(userId);
            EatenProducts eatenProduct = showEatenProductsService.getResultEatenProduct(eatenProductsList);
            String message = generalService.consumptionAnalysis(eatenProduct, userResult);
            request.setAttribute("message", message);
            request.setAttribute("eatenProduct", eatenProduct);
            request.setAttribute("eatenProductsList", eatenProductsList);
            request.getRequestDispatcher(PERSONAL_LIST_PAGE).forward(request,response);
        }

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
    private boolean verify(HttpServletRequest request, String id){
        if(Objects.isNull(id)) {
            request.setAttribute("errorMessageLogin","You didn't enter category id");
            return false;
        }
        if(!EnterDataValidator.isValidPositiveNumber(id)){
            request.setAttribute("errorMessageLogin","id incorrect");
            return false;
        }
        return true;
    */}
}
