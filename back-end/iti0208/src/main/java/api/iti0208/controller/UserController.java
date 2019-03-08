package api.iti0208.controller;

import api.iti0208.data.dto.PublicUserInfo;
import api.iti0208.data.dto.UserRegistrationInput;
import api.iti0208.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("api/register")
    public void registerUserAccount(@RequestBody @Valid UserRegistrationInput userRegistrationInput) {
        service.save(userRegistrationInput);
    }

    @GetMapping("api/user/{username}")
    public PublicUserInfo getUser(@PathVariable("username") String username) {
        return service.getPublicInfo(username);
    }

}
