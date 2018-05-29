package ua.trackingFood.exception;

import ua.trackingFood.exception.DAOException;

public class RegistrationException extends Exception {
    public RegistrationException(String mess) {
    }

    public RegistrationException(DAOException e) {
    }
}
