package ua.trackingFood.validation;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Нещерет on 01.05.2018.
 */
public class EnterDataValidator {
    private static Logger LOGGER = Logger.getLogger("EnterDataValidator.class");
    public static final int PASSWORD_LENGTH = 3;
    //login
    public static final String LOGIN_PATTERN = "[A-Za-z]+[A-Za-z0-9-_]+";
    //password
    public static final String PASSWORD_PATTERN = "[A-za-z]+[A-za-z0-9]{"+ PASSWORD_LENGTH +"}";

    //name
    public static final String NAME_PATTERN = "([A-Za-z]+( )?[^ ])+|([^ ][А-Яа-яї'\" ]+[^ ])";
    //public static final String EMAIL_PATTERN = "^[a-zA-Z0-9]{1}+((\\.|\\_-{0,1})[a-zA-Z0-9]{1})+@+([a-zA-Z0-9\\.])+";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValidLogin(String word){
        return validTextField(word, LOGIN_PATTERN, 56);
    }
    public static boolean isValidPassword(String word) {
        return validTextField(word, PASSWORD_PATTERN, PASSWORD_LENGTH+1);
    }
    public static boolean isValidEmail(String word) {
        return validTextField(word, EMAIL_PATTERN, 56);
    }
    public static boolean isValidSex(String word) {
        if(Objects.equals(word, "male") || Objects.equals(word, "female")) {
            return true;
        }
        return false;
    }
    public static boolean isValidName(String word){
        return validTextField(word, NAME_PATTERN, 56);
    }

    public static boolean isValidExpectedResult(String word){
        int number = 0;
        if(Objects.isNull(word)){
            return false;
        }
        try{
            number = Integer.parseInt(word);
        }catch (NumberFormatException e){
            return false;
        }
        if(number > 0 && number < 4){
            return  true;
        }
        return false;
    }
    public static boolean isValidLifeStyle(String word){
        int number = 0;
        if(Objects.isNull(word)){
            return false;
        }
        try{
            number = Integer.parseInt(word);
        }catch (NumberFormatException e){
            return false;
        }
        if(number > 0 && number < 6){
            return  true;
        }
        return false;
    }

    public static boolean isValidNumber(String numberStr){
        BigDecimal number;
        if(Objects.isNull(numberStr)){
            return false;
        }
        try{
            number = new BigDecimal(numberStr);
        }catch (NumberFormatException e){
            return false;
        }
        if(number.compareTo(new BigDecimal("0")) != 1){
            return false;
        }
        return true;
    }
    public static boolean isValidPositiveNumber(String numberStr){
        BigDecimal number;
        if(Objects.isNull(numberStr)){
            return false;
        }
        try{
            number = new BigDecimal(numberStr);
        }catch (NumberFormatException e){
            return false;
        }
        if(number.compareTo(new BigDecimal("0")) == -1){
            return false;
        }
        return true;
    }

    private static boolean validTextField(String word, String patternStr, int length) {
        if(!Objects.equals(word, "") && !Objects.isNull(word) && word.length() <= length) {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(word);
            if(matcher.matches()){
                return true;
            }
        }
        return false;
    }
}
