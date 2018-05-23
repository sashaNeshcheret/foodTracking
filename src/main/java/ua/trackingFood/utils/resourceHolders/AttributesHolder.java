package ua.trackingFood.utils.resourceHolders;


import ua.trackingFood.utils.propertyManagers.AttributesPropertyManager;

/**
 * Holds attributes values from attributes.properties file
 */
public class AttributesHolder {
    public static final String ATTR_COMMAND = "command";


    /*Table names*/
    public static final String GENRES_TABLE_NAME = "genres";
    public static final String PUBLISHERS_TABLE_NAME = "publishers";
    public static final String USERS_TABLE_NAME = "users";
    public static final String ROLES_TABLE_NAME = "roles";
    public static final String PERIODICALS_TABLE_NAME = "periodicals";
    public static final String ISSUES_TABLE_NAME = "periodical_issues";
    public static final String PAYMENTS_TABLE_NAME = "payments";
    public static final String SUBSCRIPTIONS_TABLE_NAME = "subscriptions";

    /*Commands*/
    //public static final String CHANGE_LANGUAGE = AttributesPropertyManager.getProperty("command.language");

    public static final String LOGIN = AttributesPropertyManager.getProperty("command.login");
    public static final String REGISTRATION = AttributesPropertyManager.getProperty("command.registration");
    public static final String LOGOUT = AttributesPropertyManager.getProperty("command.logOut");
    public static final String GO_TO_REGISTRATION = AttributesPropertyManager.getProperty("command.goToRegistration");
    public static final String GO_TO_REGISTRATION_PARAM = AttributesPropertyManager.getProperty("command.goToRegistrationParam");

    public static final String GO_TO_CHANGE_PARAM = AttributesPropertyManager.getProperty("command.goToChangeParam");
    public static final String GO_TO_GENERAL = AttributesPropertyManager.getProperty("command.goToGeneral");
    public static final String GO_TO_CHOOSE_CATEGORY = AttributesPropertyManager.getProperty("command.goToChooseCategory");
    public static final String REGISTRATION_PARAM = AttributesPropertyManager.getProperty("command.registrationParam");
    public static final String CHOOSE_CATEGORY = AttributesPropertyManager.getProperty("command.chooseCategory");

    public static final String ADD_AND_SHOW_EATEN_PRODUCTS = AttributesPropertyManager.getProperty("command.addAndShowEatenProducts");
    public static final String ADD_NEW_PRODUCT = AttributesPropertyManager.getProperty("command.addNewProduct");
    public static final String ADD_NEW_CATEGORY = AttributesPropertyManager.getProperty("command.addNewCategory");
    public static final String DELETE_EATEN_PRODUCT = AttributesPropertyManager.getProperty("command.deleteEatenProduct");

    /*Request attributes*/
    public static final String COMMAND = "command";
    public static final String ATTR_USER_ID = AttributesPropertyManager.getProperty("user.id");
    public static final String ATTR_LOGIN = AttributesPropertyManager.getProperty("user.login");
    public static final String ATTR_PASSWORD = AttributesPropertyManager.getProperty("user.password");
    public static final String ATTR_NAME = AttributesPropertyManager.getProperty("user.name");
    public static final String ATTR_SURNAME = AttributesPropertyManager.getProperty("user.surname");
    public static final String ATTR_EMAIL = AttributesPropertyManager.getProperty("user.mailAddress");
    public static final String ATTR_SEX = AttributesPropertyManager.getProperty("user.sex");
    public static final String ATTR_LIFE_STYLE = AttributesPropertyManager.getProperty("user.lifeStyle");
    public static final String ATTR_AGE = AttributesPropertyManager.getProperty("user.age");
    public static final String ATTR_HEIGHT = AttributesPropertyManager.getProperty("user.height");
    public static final String ATTR_WEIGHT = AttributesPropertyManager.getProperty("user.weight");
    public static final String ATTR_EXPECTED_RESULT = AttributesPropertyManager.getProperty("user.expectedResult");

    public static final String ATTR_CATEGORY_NAME = AttributesPropertyManager.getProperty("category.name");
    public static final String ATTR_CATEGORY_ID = AttributesPropertyManager.getProperty("category.categoryId");
    public static final String ATTR_ID = AttributesPropertyManager.getProperty("category.id");
    public static final String ATTR_PREVIOUS = AttributesPropertyManager.getProperty("category.previous");
    public static final String ATTR_NEXT = AttributesPropertyManager.getProperty("category.next");
    public static final String ATTR_PAGE_NUM = AttributesPropertyManager.getProperty("attr.pageNumber");


    public static final String ATTR_PRODUCT_ID = AttributesPropertyManager.getProperty("product.id");
    public static final String ATTR_PRODUCTS_ID = AttributesPropertyManager.getProperty("product.productId");
    public static final String ATTR_PRODUCT_NAME = AttributesPropertyManager.getProperty("product.name");
    public static final String ATTR_NUMBER = AttributesPropertyManager.getProperty("product.number");
    public static final String ATTR_ENERGY_VALUE = AttributesPropertyManager.getProperty("product.energyValue");
    public static final String ATTR_PROTEINS = AttributesPropertyManager.getProperty("product.proteins");
    public static final String ATTR_DIMENSION_1 = AttributesPropertyManager.getProperty("product.Dimension");
    public static final String ATTR_CARBOHYDRATES = AttributesPropertyManager.getProperty("product.carbohydrates");
    public static final String ATTR_DIMENSION_2 = AttributesPropertyManager.getProperty("product.Dimension2");
    public static final String ATTR_FATS = AttributesPropertyManager.getProperty("product.fats");
    public static final String ATTR_DIMENSION_3 = AttributesPropertyManager.getProperty("product.Dimension3");
    public static final String ATTR_PREVIOUS_PROD = AttributesPropertyManager.getProperty("product.previous");
    public static final String ATTR_NEXT_PROD = AttributesPropertyManager.getProperty("product.next");
    public static final String ATTR_ADD = AttributesPropertyManager.getProperty("product.add");


    public static final String ATTR_ERROR_MESSAGE = AttributesPropertyManager.getProperty("attr.errorMessage");
    public static final String ATTR_MESSAGE = AttributesPropertyManager.getProperty("attr.message");
    public static final String ATTR_ERROR_MESSAGE_PARAM = AttributesPropertyManager.getProperty("attr.errorMessageParam");
    public static final String ATTR_ERROR_MESSAGE_LOGIN = AttributesPropertyManager.getProperty("attr.errorMessageLogin");
    public static final String ATTR_ERROR_MESSAGE_ADD = AttributesPropertyManager.getProperty("attr.errorMessageAdd");






    /*additional*/
    public static final String GET = AttributesPropertyManager.getProperty("method.type.get");
    public static final String POST = AttributesPropertyManager.getProperty("method.type.post");
    public static final String PAGE_SUFFIX = AttributesPropertyManager.getProperty("page.suffix");
}
