package ua.simpleproject.comand;

import javax.servlet.http.HttpServletRequest;

public class OutYCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        return page;
    }
}
