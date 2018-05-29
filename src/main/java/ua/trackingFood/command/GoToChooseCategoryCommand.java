package ua.trackingFood.command;

import ua.trackingFood.command.Command;
import ua.trackingFood.entity.*;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static ua.trackingFood.utils.resourceHolders.PagesHolder.CHOOSE_CATEGORY_PAGE;

public class GoToChooseCategoryCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(GoToChooseCategoryCommand.class.getSimpleName());
    private static final GeneralService generalService = ServiceFactory.getServiceFactory().getGeneralService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryProducts> categoryList = generalService.readCategories();
        request.setAttribute("list", categoryList);
        LOGGER.info("method execute run");
        request.getRequestDispatcher(CHOOSE_CATEGORY_PAGE).forward(request, response);
    }
}