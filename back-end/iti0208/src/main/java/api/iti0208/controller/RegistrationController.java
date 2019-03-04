package api.iti0208.controller;

import api.iti0208.entity.Post;
import api.iti0208.entity.User;
import api.iti0208.entity.UserDto;
import api.iti0208.exception.EmailExistsException;
import api.iti0208.repository.UserRepository;
//import api.iti0208.service.UserService;
import api.iti0208.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private UserServiceImpl service;



    @GetMapping("api/register")
    @CrossOrigin(origins = "http://localhost:8080")
    public List<User> showRegisteredUsers() {
        return service.findAll();
    }

    @PostMapping("api/register")
    @CrossOrigin(origins = "http://localhost:8080")
    public String registerUserAccount(@RequestBody @Valid UserDto userDto,
                                      BindingResult result){
        System.out.println("Post meetodis");
        System.out.println(userDto.getFirstName());
        User existing = service.findByEmail(userDto.getEmail());
        if (existing != null){

            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            System.out.println("Probleem salvestamisel");
            return result.getAllErrors() + "";
        }

        service.save(userDto);
        System.out.println("Salvestatud");
        return "redirect:/registration?success";
    }



}
