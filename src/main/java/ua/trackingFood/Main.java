/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package ua.trackingFood;

import ua.trackingFood.service.LoginService;
import ua.trackingFood.transactions.ConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static  void main(String[] arg){
/*        if (ConnectionPool.getConnectionPool().getDataSource() == null) {
            try {
                Context context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/tracking_food");//
                ConnectionPool.getConnectionPool().setDataSource(dataSource);
            } catch (NamingException e) {
                System.out.println("error");
//logger.error("Error in init method. misplaced name of the database location\n");
            }
            LoginService loginService = new LoginService();
            loginService.checkLogin("gfdfds","gfds4567");
        }

    }
    private static boolean validTextField(String word, String pattern, int length) {
        if(!Objects.equals(word, "") && !Objects.isNull(word) && word.length() < length) {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(word);
            if(matcher.matches()){
                return true;
            }
        }
        return false;
    */}
}
