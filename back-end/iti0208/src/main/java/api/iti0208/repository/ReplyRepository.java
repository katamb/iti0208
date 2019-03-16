package api.iti0208.repository;

import api.iti0208.data.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ReplyRepository extends JpaRepository<Reply, Long> {




}
