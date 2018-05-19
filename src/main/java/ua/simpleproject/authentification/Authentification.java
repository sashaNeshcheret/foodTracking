package ua.simpleproject.authentification;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Authentification {
    private Authentification() {
    }

    public static boolean isUserLogIn(HttpSession session){
        return Objects.nonNull(session.getAttribute("login"));
    }
}
