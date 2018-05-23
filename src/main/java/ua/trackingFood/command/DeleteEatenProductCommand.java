package ua.trackingFood.command;

import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.UserContact;
import ua.trackingFood.service.AddEatenProductsService;
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

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_CATEGORY_ID;
import static ua.trackingFood.utils.resourceHolders.AttributesHolder.ATTR_PRODUCTS_ID;
import static ua.trackingFood.utils.resourceHolders.AttributesHolder.LOGIN;
import static ua.trackingFood.utils.resourceHolders.PagesHolder.PERSONAL_LIST_PAGE;

public class DeleteEatenProductCommand implements Command {
    private Logger logger = Logger.getLogger("GoToChangeParamCommand.class");
    private GeneralService generalService = new GeneralService();
    private LoginService loginService = new LoginService();
    private ShowEatenProductsService showEatenProductsService = new ShowEatenProductsService();
    private AddEatenProductsService addEatenProductsService = new AddEatenProductsService();

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
            logger.warning("id продукту для видалення неправильний");
        }
        UserContact userContact = loginService.getUserByLogin(login);
        //UserParam userParam = generalService.readUserParamInfo(userContact.getId());
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
        //request.setAttribute("userParam", userParam);
        request.getRequestDispatcher(PERSONAL_LIST_PAGE).forward(request, response);
    }
}