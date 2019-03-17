package api.iti0208.controller;

import api.iti0208.data.dto.PublicUserInfo;
import api.iti0208.data.dto.UserRegistrationInput;
import api.iti0208.data.entity.AppUser;
import api.iti0208.data.entity.Post;
import api.iti0208.data.entity.Reply;
import api.iti0208.repository.UserRepository;
import api.iti0208.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static api.iti0208.security.SecurityConstants.HEADER_STRING;
import static api.iti0208.service.UserService.getUsernameFromJwtToken;

@RestController
public class UserController {

    private final UserService service;
    private final UserRepository userRepo;

    @Autowired
    public UserController(UserService service, UserRepository userRepo) {

        this.service = service;
        this.userRepo = userRepo;
    }

    @PostMapping("api/register")
    public void registerUserAccount(@RequestBody @Valid UserRegistrationInput userRegistrationInput) {
        service.save(userRegistrationInput);
    }

    @GetMapping("api/user/{username}")
    @PreAuthorize("#username == authentication.name || hasAuthority('ROLE_ADMIN')")
    public PublicUserInfo getUser(@PathVariable("username") String username) {
        return service.getPublicInfo(username);
    }

    @GetMapping("api/usersPosts")
    //@PreAuthorize("#username == authentication.name || hasAuthority('ROLE_ADMIN')")
    public Set<Post> getUsers(@RequestHeader(value = HEADER_STRING) String header) {
        String username = getUsernameFromJwtToken(header);
        return userRepo.findByUsername(username).getUserPosts();
    }

    @GetMapping("api/usersReplies")
    //@PreAuthorize("#username == authentication.name || hasAuthority('ROLE_ADMIN')")
    public Set<Reply> getRepleis(@RequestHeader(value = HEADER_STRING) String header) {
        String username = getUsernameFromJwtToken(header);
        return userRepo.findByUsername(username).getUserReplies();
    }


}
