package ua.trackingFood.service;

import ua.trackingFood.dao.*;
import ua.trackingFood.dao.Impl.*;
import ua.trackingFood.service.AddEatenProductsService;
import ua.trackingFood.service.AddNewCategoryService;
import ua.trackingFood.service.AddNewProductService;
import ua.trackingFood.service.GeneralService;
import ua.trackingFood.service.LoginService;
import ua.trackingFood.service.RegistrationService;
import ua.trackingFood.service.ShowEatenProductsService;

public class ServiceFactory {
    private static  ServiceFactory serviceFactory  = new ServiceFactory();
    private ua.trackingFood.service.AddEatenProductsService addEatenProductsService = new ua.trackingFood.service.AddEatenProductsService();
    private ua.trackingFood.service.AddNewCategoryService addNewCategoryService = new ua.trackingFood.service.AddNewCategoryService();
    private ua.trackingFood.service.AddNewProductService addNewProductService = new ua.trackingFood.service.AddNewProductService();
    private ua.trackingFood.service.GeneralService generalService = new ua.trackingFood.service.GeneralService();
    private ua.trackingFood.service.LoginService loginService = new ua.trackingFood.service.LoginService();
    private ua.trackingFood.service.RegistrationService registrationService = new ua.trackingFood.service.RegistrationService();
    private ua.trackingFood.service.ShowEatenProductsService showEatenProductsService = new ua.trackingFood.service.ShowEatenProductsService();

    private ServiceFactory(){
    }

    public static ServiceFactory getServiceFactory() {
        return serviceFactory;
    }
    public AddEatenProductsService getAddEatenProductsService() {
        return addEatenProductsService;
    }
    public AddNewCategoryService getAddNewCategoryService() {
        return addNewCategoryService;
    }
    public AddNewProductService getAddNewProductService() {
        return addNewProductService;
    }
    public GeneralService getGeneralService() {
        return generalService;
    }
    public LoginService getLoginService(){ return loginService;}
    public RegistrationService getRegistrationService(){ return registrationService;}
    public ShowEatenProductsService getShowEatenProductsService(){ return showEatenProductsService;}
}
