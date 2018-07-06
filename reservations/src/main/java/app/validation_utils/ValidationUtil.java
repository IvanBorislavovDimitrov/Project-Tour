package app.validation_utils;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidationUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> boolean isValid(T obj) {
        return obj != null && validator.validate(obj).size() == 0;
    }
}
