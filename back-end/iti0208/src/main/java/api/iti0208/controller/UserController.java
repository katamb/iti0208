package api.iti0208.controller;

import api.iti0208.entity.AppUser;
import api.iti0208.entity.UserDto;
import api.iti0208.exception.EmailExistsException;
import api.iti0208.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.service = service;
    }

    /*@GetMapping("api/register")
    @CrossOrigin(origins = "http://localhost:8080")
    public List<AppUser> showRegisteredUsers() {
        return service.findAll();
    }*/

    @PostMapping("api/register")
    @CrossOrigin(origins = "http://localhost:8080")
    public void registerUserAccount(@RequestBody @Valid UserDto userDto){
        AppUser existing = service.findByEmail(userDto.getEmail());
        if (existing != null){
            throw new EmailExistsException("This email is already registered!");
        }

        service.save(userDto);
    }



}
