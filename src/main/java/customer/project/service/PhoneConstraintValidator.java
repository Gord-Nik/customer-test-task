package customer.project.service;

import customer.project.lib.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone constraint) {
    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext context) {
        if(phoneField == null) {
            return false;
        }
        return phoneField.matches("^\\+[1-9]{6,14}$");
    }
}
