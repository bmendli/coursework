package polytech.spbstu.controller;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polytech.spbstu.dto.ResultDto;
import polytech.spbstu.dto.UserDto;
import polytech.spbstu.entity.UserEntity;
import polytech.spbstu.service.SecurityService;
import polytech.spbstu.service.UserService;
import polytech.spbstu.validator.UserValidator;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})
@RestController
@RequestMapping("/polyclinic/spbstu/users/")
public class UserRestController {

    private final UserService userService;

    private final SecurityService securityService;

    private final UserValidator validator;

    private final Base64.Decoder decoder = Base64.getDecoder();

    @Autowired
    public UserRestController(UserService userService, SecurityService securityService, UserValidator validator) {
        this.userService = userService;
        this.securityService = securityService;
        this.validator = validator;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserEntity userEntity = userService.findById(id);
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        final UserDto userDto = UserDto.fromUser(userEntity);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value = "registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());

        return "registration";
    }

    @PostMapping(value = "registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDto> registration(@RequestBody UserEntity userEntity,
                                                  BindingResult bindingResult, Model model, Error error) {
        validator.validate(userEntity, bindingResult);
        ResultDto resultDto = new ResultDto();

        if (bindingResult.hasErrors()) {
            resultDto.setResult(false);
            return new ResponseEntity<>(resultDto, HttpStatus.CONFLICT);
        }

        userService.register(userEntity);
        securityService.autoLogin(userEntity.getUsername(), userEntity.getPassword());
        resultDto.setResult(true);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PostMapping(value = "login")
    public ResponseEntity<ResultDto> login(@RequestBody UserEntity userEntity,
                                           Model model, String error, String logout) {
        final UserEntity user = userService.findByUsername(userEntity.getUsername());
        ResultDto resultDto = new ResultDto();
        resultDto.setResult(false);
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        if (UserValidator.isEmpty(user) || !UserValidator.matchPassword(userEntity.getPassword(), user.getPassword())) {
            model.addAttribute("login", "User doesn't exist");
        } else if (error != null) {
            model.addAttribute("error", "Username of password is incorrect");
        } else if (logout != null) {
            model.addAttribute("logout", "Logged out successfully");
        } else {
            resultDto.setResult(true);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(resultDto, status);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService
                .getAll()
                .stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
