package ua.simpleproject.comand;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/login.jsp";
// уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}