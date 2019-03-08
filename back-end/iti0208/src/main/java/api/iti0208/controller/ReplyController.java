package api.iti0208.controller;

import api.iti0208.data.entity.Reply;
import api.iti0208.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static api.iti0208.security.SecurityConstants.*;
import static api.iti0208.service.UserService.getUsernameFromJwtToken;

@RestController
public class ReplyController {

    private final ReplyRepository replyRepo;

    @Autowired
    public ReplyController(ReplyRepository replyRepo) {
        this.replyRepo = replyRepo;
    }

    @PostMapping("api/add/reply")
    @Transactional
    public Reply save(@RequestBody @Valid Reply item,
                      @RequestHeader(value = HEADER_STRING) String header) {
        if (header != null) {
            String username = getUsernameFromJwtToken(header);
            if (username != null) {
                item.setPostedBy(username);
            }
        }
        return replyRepo.save(item);
    }

}
