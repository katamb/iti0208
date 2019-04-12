package api.iti0208.service;

import api.iti0208.data.entity.Post;
import api.iti0208.data.entity.Reply;
import api.iti0208.data.input.ReplyInput;
import api.iti0208.data.input.ReplyPatchInput;
import api.iti0208.exception.BadRequestException;
import api.iti0208.repository.PostRepository;
import api.iti0208.repository.ReplyRepository;
import api.iti0208.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static api.iti0208.service.UserService.getUsernameFromJwtToken;

@Service
public class ReplyService {

    private final ReplyRepository replyRepo;
    private final UserRepository userRepo;
    private final PostRepository postRepo;

    public ReplyService(ReplyRepository replyRepo, UserRepository userRepo, PostRepository postRepo) {
        this.replyRepo = replyRepo;
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    public Reply save(ReplyInput input, String header) {
        Reply item = new Reply();
        Optional<Post> relatedPost = postRepo.findById(input.getPostId());

        if (header != null) {
            String username = getUsernameFromJwtToken(header);
            if (username != null && relatedPost.isPresent()) {
                item.setReply(input.getReply());
                item.setFileLocation(input.getFileLocation());
                item.setPost(relatedPost.get());
                //item.setPostedBy(username);
                //item.setUserId(userRepo.findIdByUsername(username));
                item.setPostedBy(userRepo.findByUsername(username));
            }
        }

        return replyRepo.save(item);
    }

    public void delete(Long id) {
        replyRepo.deleteById(id);
    }

    public Reply patch(ReplyPatchInput obj, Long id) {
        Optional<Reply> reply = replyRepo.findById(id);

        if (reply.isPresent()) {
            String newReply = obj.getReply();

            if (newReply != null && newReply.length() != 0) {
                replyRepo.updateReply(id, newReply);
            }

            return replyRepo.findById(id).get();
        }

        throw new BadRequestException("Problem updating Your reply!");
    }

    public String findUsernameOfReplier(Long id) {
        Optional<Reply> reply = replyRepo.findById(id);
        if (reply.isPresent()) {
            return reply.get().getPostedBy().getUsername();
        }
        throw new BadRequestException("This item no longer exists!");
    }
}
