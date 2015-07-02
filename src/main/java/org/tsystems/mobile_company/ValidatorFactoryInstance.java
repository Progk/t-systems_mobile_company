package org.tsystems.mobile_company;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by sergey on 02.07.15.
 */
public class ValidatorFactoryInstance {
    private static ValidatorFactory validatorFactoryInstance;

    static {
        validatorFactoryInstance = Validation.buildDefaultValidatorFactory();
    }

    public static ValidatorFactory getInstance() {
        return validatorFactoryInstance;
    }

    public static Validator getValidator() {
        return validatorFactoryInstance.getValidator();
    }

    private ValidatorFactoryInstance() {

    }
}
