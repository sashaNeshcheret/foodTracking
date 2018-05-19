package ua.simpleproject.configaration;

import ua.simpleproject.command.FactoryCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SecurityConfiguraton {
    private static final SecurityConfiguraton config = new SecurityConfiguraton();

    private Map<String,String> grant = new HashMap<>();

    public static SecurityConfiguraton getInstance(){
        return config;
    }

    private SecurityConfiguraton() {
        grant.put(FactoryCommand.LOGIN,"ALL");
        grant.put(FactoryCommand.REGISTER,"ALL");
        grant.put(FactoryCommand.LOGOUT,"AUTH");
        grant.put(FactoryCommand.USERS,"AUTH");
        grant.put("/","ALL");
    }

    public String security(String command){
        return grant.get(command);
    }

    public Set<String> getEndPoints(){
        return grant.keySet();
    }
}
