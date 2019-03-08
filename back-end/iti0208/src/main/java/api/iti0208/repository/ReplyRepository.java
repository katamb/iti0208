package api.iti0208.repository;

import api.iti0208.data.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
