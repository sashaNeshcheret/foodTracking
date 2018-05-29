package ua.trackingFood.command;

import ua.trackingFood.command.AddNewCategoryCommand;
import ua.trackingFood.command.AddNewProductCommand;
import ua.trackingFood.command.ChooseCategoryCommand;
import ua.trackingFood.command.ChooseEatenProductsCommand;
import ua.trackingFood.command.Command;
import ua.trackingFood.command.DeleteEatenProductCommand;
import ua.trackingFood.command.EmptyCommand;
import ua.trackingFood.command.GeneralCommand;
import ua.trackingFood.command.GoToChangeParamCommand;
import ua.trackingFood.command.GoToChooseCategoryCommand;
import ua.trackingFood.command.GoToRegistrationCommand;
import ua.trackingFood.command.GoToRegistrationParamCommand;
import ua.trackingFood.command.LogOutCommand;
import ua.trackingFood.command.LoginCommand;
import ua.trackingFood.command.RegistrationCommand;
import ua.trackingFood.command.RegistrationParamCommand;

import java.util.HashMap;
import java.util.Map;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;

public class FactoryCommand {
    private static final FactoryCommand FACTORY_COMMAND = new FactoryCommand();
    private Map<String, ua.trackingFood.command.Command> commandMap = new HashMap<>();

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
        commandMap.put(ADD_AND_SHOW_EATEN_PRODUCTS,new ChooseEatenProductsCommand());
        commandMap.put(ADD_NEW_PRODUCT,new AddNewProductCommand());
        commandMap.put(ADD_NEW_CATEGORY,new AddNewCategoryCommand());
        commandMap.put(DELETE_EATEN_PRODUCT,new DeleteEatenProductCommand());

    }

    public static FactoryCommand getInstance(){
        return FACTORY_COMMAND;
    }

    public Command getCommand(String command){
        return commandMap.get(command);

    }

}
