package api.iti0208.controller;

import api.iti0208.entity.Reply;
import api.iti0208.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ReplyController {

    private final ReplyRepository dao;

    @Autowired
    public ReplyController(ReplyRepository dao) {
        this.dao = dao;
    }

    @PostMapping("api/add/reply")
    @CrossOrigin(origins = "http://localhost:8080")
    @Transactional
    public Reply save(@RequestBody @Valid Reply item) {
        return dao.save(item);
    }

}
