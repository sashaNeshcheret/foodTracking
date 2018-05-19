package ua.simpleproject.command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class FactoryCommand {
    private static final FactoryCommand factoryCommand = new FactoryCommand();
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";
    public static final String USERS = "/users";

    private Map<String,Command> commandMap = new HashMap<>();

    private FactoryCommand() {
        commandMap.put(LOGIN,new LoginCommand());
        commandMap.put("/",new LoginCommand());
        commandMap.put(REGISTER,new RegistrationCommand());
        //commandMap.put(LOGOUT,new LogoutCommand());
        //commandMap.put(USERS,new UsersCommand());
    }

    public static FactoryCommand getInstance(){
        return factoryCommand;
    }

    public Command getCommand(String command){
        return commandMap.get(command);

    }

}
