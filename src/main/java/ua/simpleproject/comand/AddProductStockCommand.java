package ua.simpleproject.comand;

import org.apache.log4j.Logger;
import ua.simpleproject.command.LoginCommand;
import ua.simpleproject.entity.Product;
import ua.simpleproject.services.AddProductStockLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Objects;

public class AddProductStockCommand implements ActionCommand{
    private AddProductStockLogic addProductStockLogic = new AddProductStockLogic();
    private Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        Product product = new Product();
        String page = null;
//  взяти назву або код товару і їх кількість з запиту
        String name = (String) request.getParameter("name");
        String codeStr = (String) request.getParameter("code");
        String numberStr = (String) request.getParameter("number");

        product.setName(name);

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if (Objects.isNull(login)){
            page = "/jsp//login.jsp";
            logger.error("");
            return page;
        }
        try{
            int code = Integer.parseInt(codeStr);
            BigDecimal number = new BigDecimal(numberStr);
            product.setCodeProduct(code);
            addProductStockLogic.addProductStock(product, number);
            page = "/jsp/general.jsp";
            return page;
        }catch(Exception e){
            page = "/jsp//error/error.jsp";
            logger.error("", e);
            return page;
        }
    }
}
