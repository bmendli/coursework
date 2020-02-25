package polytech.spbstu.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import polytech.spbstu.entity.UserEntity;
import polytech.spbstu.service.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEntity userEntity = (UserEntity) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        final int usernameLength = userEntity.getUsername().length();
        if (usernameLength < 8 || usernameLength > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(userEntity.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        final int passwordLength = userEntity.getPassword().length();
        if (passwordLength < 8 || passwordLength > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userEntity.getPassword().equals(userEntity.getConfirmPassword())) {
            errors.rejectValue("password", "Different.userForm.password");
        }
    }
}
