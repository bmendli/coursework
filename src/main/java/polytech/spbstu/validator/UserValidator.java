package polytech.spbstu.validator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import polytech.spbstu.entity.UserEntity;
import polytech.spbstu.service.UserService;

@Component
public class UserValidator implements Validator {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService,
                         BCryptPasswordEncoder encoder) {
        this.userService = userService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(@NotNull Object object, @NotNull Errors errors) {
        UserEntity userEntity = (UserEntity) object;

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
    }

    public static boolean isEmpty(@Nullable UserEntity userEntity) {
        return userEntity == null || userEntity.getUsername() == null || userEntity.getPassword() == null;
    }

    public static boolean matchPassword(@NotNull final String password, @NotNull final String passwordFromDb) {
        return encoder.matches(password, passwordFromDb);
    }
}
