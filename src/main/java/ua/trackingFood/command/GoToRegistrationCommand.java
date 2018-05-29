package ua.trackingFood.command;

import ua.trackingFood.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static ua.trackingFood.utils.resourceHolders.PagesHolder.REGISTRATION_PAGE;

public class GoToRegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
    }
}
