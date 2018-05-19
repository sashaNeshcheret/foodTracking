package ua.trackingFood.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class GoToRegistrationCommand implements Command {
    private Logger logger = Logger.getLogger("GoToRegistrationCommand.class");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
    }
}
