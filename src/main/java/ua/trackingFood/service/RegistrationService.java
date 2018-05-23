package ua.trackingFood.service;

import ua.trackingFood.dao.DAOLifeStyle;
import ua.trackingFood.dao.DAOUserResult;
import ua.trackingFood.dao.DAOUsers;
import ua.trackingFood.dao.DAOUserParam;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.LifeStyle;
import ua.trackingFood.entity.UserContact;
import ua.trackingFood.entity.UserParam;
import ua.trackingFood.entity.UserResult;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.exception.RegistrationException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Нещерет on 02.05.2018.
 */
public class RegistrationService {
    private DAOUsers daoUsers = DAOFactory.getDaoFactory().getDAOUsers();
    private DAOUserParam daoUserParam = DAOFactory.getDaoFactory().getDAOUserParam();
    private DAOUserResult daoUserResult = DAOFactory.getDaoFactory().getDAOUserResult();
    private DAOLifeStyle daoLifeStyle = DAOFactory.getDaoFactory().getDAOLifeStyle();
    private Logger logger = Logger.getLogger("RegistrationService.class");



    public void register(UserContact userContact) throws RegistrationException {
        try {
            daoUsers.create(userContact);
        } catch (DAOException e) {
            throw new RegistrationException(e);
        }
    }
    public void registerStep2(UserParam userParam, UserContact userContact) throws RegistrationException {
        if(Objects.isNull(userParam) || Objects.isNull(userContact)){
            throw new RegistrationException("UserParam or UserContact is null");
        }
        try {
            UserParam userParamExists = daoUserParam.read(userContact.getId());
            if(Objects.isNull(userParamExists)){
                LifeStyle lifeStyle = daoLifeStyle.read(userParam.getLifeStyleId());
                UserResult userResult = createResults(userParam, lifeStyle);
                daoUserParam.create(userParam);
                daoUserResult.create(userResult);
            }else{
                LifeStyle lifeStyle = daoLifeStyle.read(userParam.getLifeStyleId());
                UserResult userResult = createResults(userParam, lifeStyle);
                daoUserParam.update(userParam);
                daoUserResult.update(userResult);
            }
        } catch (DAOException e) {
            throw  new RegistrationException(e);
        }
    }
    public UserResult createResults(UserParam userParam, LifeStyle lifeStyle) {
        BigDecimal weight = new BigDecimal("13.397").multiply(userParam.getWeight());
        weight.setScale(3, BigDecimal.ROUND_HALF_UP);
        BigDecimal height = new BigDecimal("4.799").multiply(userParam.getHeight());
        height.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal age = new BigDecimal("5.677").multiply(new BigDecimal(userParam.getAge()));
        age.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal levelMetabolism = (new BigDecimal("88.362")).add(weight
                .add(height).
                subtract(age));
        levelMetabolism.setScale(2, BigDecimal.ROUND_HALF_UP);

//BigDecimal met = new BigDecimal("231.23");
        BigDecimal normaCalories = levelMetabolism.multiply(lifeStyle.getIndex());
BigDecimal expectedNormCalories = normaCalories.multiply(new BigDecimal(0.75));
        BigDecimal proteins = expectedNormCalories.multiply(new BigDecimal(0.35));
        BigDecimal carbohydrates = expectedNormCalories.multiply(new BigDecimal(0.45));
        BigDecimal fats = expectedNormCalories.multiply(new BigDecimal(0.2));
        UserResult userResult = new UserResult(userParam.getUserId(), levelMetabolism, normaCalories,expectedNormCalories, proteins, carbohydrates, fats);
        return userResult;
    }
}
