package api.iti0208.controller;

import api.iti0208.data.output.PublicUserInfo;
import api.iti0208.data.input.UserRegistrationInput;
import api.iti0208.data.entity.Post;
import api.iti0208.data.entity.Reply;
import api.iti0208.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static api.iti0208.security.SecurityConstants.HEADER_STRING;
import static api.iti0208.service.UserService.getUsernameFromJwtToken;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/register")
    public void registerUserAccount(@RequestBody @Valid UserRegistrationInput userRegistrationInput) {
        userService.save(userRegistrationInput);
    }

    @GetMapping("api/user/{username}")
    @PreAuthorize("#username == authentication.name || hasAuthority('ROLE_ADMIN')")
    public PublicUserInfo getUser(@PathVariable("username") String username) {
        return userService.getPublicInfo(username);
    }

    @GetMapping("api/usersPosts")
    @PreAuthorize("@userService.getUsernameFromJwt(#header) == authentication.name || hasAuthority('ROLE_ADMIN')")
    public Set<Post> getUserPosts(@RequestHeader(value = HEADER_STRING) String header) {
        String username = getUsernameFromJwtToken(header);
        return userService.getUserPosts(username);
    }

    @GetMapping("api/usersReplies")
    @PreAuthorize("@userService.getUsernameFromJwt(#header) == authentication.name || hasAuthority('ROLE_ADMIN')")
    public Set<Reply> getReplies(@RequestHeader(value = HEADER_STRING) String header) {
        String username = getUsernameFromJwtToken(header);
        return userService.getUserReplies(username);
    }


}
