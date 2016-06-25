package pl.pb.web.spring.thymeleafe.controllers.validators;

import pl.pb.web.spring.thymeleafe.reposytory.model.ContactEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

/**
 * Created by pawel on 2016-02-14.
 */
@Component
public class ContactValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ContactEntity.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {

        if (o != null && o instanceof ContactEntity) {
            String numberValue = ((ContactEntity) o).getNumber();
            if (numberValue != null) {
                numberValue = numberValue.trim().replace(" ", "");
                if (!numberValue.matches("\\d+")) {
                    Object[] attr = new Object[2];
                    errors.rejectValue("number","contact.validation.number","Value must contain only digits.");
                }
            }
        }
    }
}
