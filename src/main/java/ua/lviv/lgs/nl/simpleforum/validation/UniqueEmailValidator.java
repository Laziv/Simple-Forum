package ua.lviv.lgs.nl.simpleforum.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ua.lviv.lgs.nl.simpleforum.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    @Autowired
    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.isEmailAlreadyInUse(value);
    }


}