package ua.trackingFood.command;

import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.entity.UserResult;
import ua.trackingFood.service.AddEatenProductsService;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.ShowEatenProductsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

public class AddAndShowEatenProductsCommand implements Command{
    private GeneralService generalService = new GeneralService();
    private AddEatenProductsService addEatenProductsService = new AddEatenProductsService();
    private ShowEatenProductsService showEatenProductsService = new ShowEatenProductsService();
    private Logger logger = Logger.getLogger("LoginCommand.class");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, BigDecimal> map = new HashMap<>();
        int userId = (int)request.getSession().getAttribute("userId");
        String categoryId = request.getParameter("categoryId");

        String nameSubmit1 = request.getParameter("previous category"); //name1 = "previous category"
        String nameSubmit2 = request.getParameter("next category"); //name2 = "next category"
        String nameSubmit3 = request.getParameter("Add All Products"); //name3 = "Add All Products"


        String[] arrayId = request.getParameterValues("id");
        String[] arrayNumber = request.getParameterValues("number");
        try{
            map = createMap(arrayId, arrayNumber);
        }catch(NumberFormatException e){
            request.setAttribute("errorMessage", "enter data is not correct, please try again");
            List<Product> productsList = generalService.getProductsList(Integer.parseInt(categoryId));
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("productsList", productsList);
            request.getRequestDispatcher("/WEB-INF/jsp/productsList.jsp").forward(request,response);
        }
//map.forEach((k,v)->(k + v));
        EatenProducts eatenProducts = null;
        for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
            Product product = showEatenProductsService.getProduct(Integer.parseInt(entry.getKey()));
            addEatenProductsService.createEatenProducts(product, entry.getValue(), userId);
        }

        List<EatenProducts> eatenProductsList = showEatenProductsService.getEatenProductList(userId);

        if(Objects.equals("Add All Products", nameSubmit3)){
            UserResult userResult = generalService.readUserResultInfo(userId);
            EatenProducts eatenProduct = showEatenProductsService.getResultEatenProduct(eatenProductsList);
            String message = generalService.consumptionAnalysis(eatenProduct, userResult);
//String messageWarning = generalService.consumptionAnalysis(eatenProduct, userResult);
//String messageError = generalService.consumptionAnalysis(eatenProduct, userResult);
            request.setAttribute("message", message);
//request.setAttribute("messageWarning", messageWarning);
//request.setAttribute("messageError", messageError);
            request.setAttribute("eatenProductsList", eatenProductsList);
            request.getRequestDispatcher("/WEB-INF/jsp/personalList.jsp").forward(request,response);
        }
        if(Objects.equals("previous category", nameSubmit1)){
            int category = Integer.parseInt(categoryId);
            List<Product> productsList = generalService.getProductsList(category-1);
            request.setAttribute("categoryId", category-1);
            request.setAttribute("productsList", productsList);
            request.getRequestDispatcher("/WEB-INF/jsp/productsList1.jsp").forward(request,response);
        }
        if(Objects.equals("next category", nameSubmit2)){
            int category = Integer.parseInt(categoryId);
            List<Product> productsList = generalService.getProductsList(category+1);
            request.setAttribute("categoryId", category+1);
            request.setAttribute("productsList", productsList);
            request.getRequestDispatcher("/WEB-INF/jsp/productsList1.jsp").forward(request,response);
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
}
