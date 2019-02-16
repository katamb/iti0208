package api.iti0208.controller;

import api.iti0208.entity.Reply;
import api.iti0208.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReplyController {

    private final ReplyRepository dao;

    @Autowired
    public ReplyController(ReplyRepository dao) {
        this.dao = dao;
    }

    @PostMapping("api/addreply")
    @CrossOrigin(origins = "http://localhost:8080")
    public Reply save(@RequestBody Reply item) {
        return dao.save(item);
    }

}
