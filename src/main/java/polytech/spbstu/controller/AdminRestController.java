package polytech.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polytech.spbstu.dto.UserDto;
import polytech.spbstu.entity.UserEntity;
import polytech.spbstu.service.UserService;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})
@RestController
@RequestMapping("/polyclinic/spbstu/admin/")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserEntity userEntity = userService.findById(id);
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        final UserDto userDto = UserDto.fromUser(userEntity);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
