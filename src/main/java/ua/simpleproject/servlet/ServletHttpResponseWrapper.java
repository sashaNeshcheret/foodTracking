package ua.simpleproject.servlet;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.Objects;

public class ServletHttpResponseWrapper extends HttpServletResponseWrapper {
    public ServletHttpResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void setHeader(String name, String value){
        if (Objects.equals(name,"MyHeader")){
            return;
        }
        super.setHeader(name,value);
    }
}
