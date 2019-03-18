package api.iti0208.controller;

import api.iti0208.data.entity.Reply;
import api.iti0208.repository.ReplyRepository;
import api.iti0208.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.Map;

import static api.iti0208.security.SecurityConstants.*;
import static api.iti0208.service.UserService.getUsernameFromJwtToken;

@RestController
public class ReplyController {

    private final ReplyRepository replyRepo;
    private final UserRepository userRepo;

    @Autowired
    public ReplyController(ReplyRepository replyRepo, UserRepository userRepo) {

        this.replyRepo = replyRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("api/add/reply")
    //@Transactional
    public Reply save(@RequestBody @Valid Reply item,
                      @RequestHeader(value = HEADER_STRING) String header) {
        if (header != null) {
            String username = getUsernameFromJwtToken(header);
            if (username != null) {
                item.setPostedBy(username);
                item.setUserId(userRepo.findByUsername(username).getId());
            }
        }
        return replyRepo.save(item);
    }

    @DeleteMapping("api/delete/reply/{id}")
    public void delete(@PathVariable Long id) {
        replyRepo.deleteById(id);
    }

    @PostMapping("api/edit/reply/{id}")
    public Reply save(@RequestBody Object obj, @PathVariable Long id) {

        if (replyRepo.findById(id).isPresent()) {
            Reply editReply = replyRepo.findById(id).get();
            String newReply = (String)((Map)obj).get("reply");
            System.out.println(newReply);
            editReply.setReply(newReply);
            return replyRepo.save(editReply);

        }
        return null;

    }

}
