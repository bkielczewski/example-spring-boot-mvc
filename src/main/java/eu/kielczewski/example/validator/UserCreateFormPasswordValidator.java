package eu.kielczewski.example.validator;

import eu.kielczewski.example.domain.UserCreateForm;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserCreateFormPasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        if (UserCreateForm.class.isAssignableFrom(clazz)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateForm form = (UserCreateForm) target;
        if (!form.getPassword1().equals(form.getPassword2())) {
            errors.rejectValue("password2", "user.error.password.no_match");
        }
    }
}
