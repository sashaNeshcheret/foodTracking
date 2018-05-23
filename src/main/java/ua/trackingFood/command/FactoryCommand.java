package ua.trackingFood.command;

import java.util.HashMap;
import java.util.Map;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;

public class FactoryCommand {
    private static final FactoryCommand factoryCommand = new FactoryCommand();
    private Map<String,Command> commandMap = new HashMap<>();

    private FactoryCommand() {
        commandMap.put(null ,new EmptyCommand());
        commandMap.put(LOGIN,new LoginCommand());
        commandMap.put(LOGOUT,new LogOutCommand());
        commandMap.put(GO_TO_REGISTRATION,new GoToRegistrationCommand());
        commandMap.put(GO_TO_REGISTRATION_PARAM,new GoToRegistrationParamCommand());
        commandMap.put(GO_TO_GENERAL,new GeneralCommand());
        commandMap.put(GO_TO_CHANGE_PARAM,new GoToChangeParamCommand());
        commandMap.put(GO_TO_CHOOSE_CATEGORY,new GoToChooseCategoryCommand());
        commandMap.put(REGISTRATION,new RegistrationCommand());
        commandMap.put(REGISTRATION_PARAM,new RegistrationParamCommand());
        commandMap.put(CHOOSE_CATEGORY,new ChooseCategoryCommand());
        commandMap.put(ADD_AND_SHOW_EATEN_PRODUCTS,new AddAndShowEatenProductsCommand());
        commandMap.put(ADD_NEW_PRODUCT,new AddNewProductCommand());
        commandMap.put(ADD_NEW_CATEGORY,new AddNewCategoryCommand());
        commandMap.put(DELETE_EATEN_PRODUCT,new DeleteEatenProductCommand());

    }

    public static FactoryCommand getInstance(){
        return factoryCommand;
    }

    public Command getCommand(String command){
        return commandMap.get(command);

    }

}
