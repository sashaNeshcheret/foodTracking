package ua.trackingFood.command;

import java.util.HashMap;
import java.util.Map;

public class FactoryCommand {
    private static final FactoryCommand factoryCommand = new FactoryCommand();
    public static final String LOGIN = "login";
    public static final String GoToREGISTER = "goToRegistration";
    public static final String GoToREGISTRATIONParam = "goToRegistrationParam";
    public static final String GoToChangeParam = "goToChangeParam";
    public static final String GoToGENERAL = "goToGeneral";
    public static final String GoToChooseCategory = "goToChooseCategory";
    public static final String REGISTRATION = "registration";
    public static final String REGISTRATIONParam = "registrationParam";
    public static final String CHOOSECategory = "chooseCategory";
    public static final String AddAndShowEatenProducts = "addAndShowEatenProducts";
    public static final String LOGOUT = "logOut";

    private Map<String,Command> commandMap = new HashMap<>();

    private FactoryCommand() {
        commandMap.put(null ,new EmptyCommand());
        commandMap.put(LOGIN,new LoginCommand());
        commandMap.put(LOGOUT,new LogOutCommand());
        commandMap.put(GoToREGISTER,new GoToRegistrationCommand());
        commandMap.put(GoToREGISTRATIONParam,new GoToRegistrationParamCommand());
        commandMap.put(GoToGENERAL,new GeneralCommand());
        commandMap.put(GoToChangeParam,new GoToChangeParamCommand());
        commandMap.put(GoToChooseCategory,new GoToChooseCategoryCommand());
        commandMap.put(REGISTRATION,new RegistrationCommand());
        commandMap.put(REGISTRATIONParam,new RegistrationParamCommand());
        commandMap.put(CHOOSECategory,new ChooseCategoryCommand());
        commandMap.put(AddAndShowEatenProducts,new AddAndShowEatenProductsCommand());

    }

    public static FactoryCommand getInstance(){
        return factoryCommand;
    }

    public Command getCommand(String command){
        return commandMap.get(command);

    }

}
