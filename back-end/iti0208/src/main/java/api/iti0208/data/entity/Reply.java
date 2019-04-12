package api.iti0208.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5)
    private String reply;

    @NotNull
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "file_location")
    private String fileLocation;

    /*@OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    @LazyToOne(LazyToOneOption.FALSE)
    private AppUser userPosts;*/

    @Column(name = "user_id")
    private long userId;

    @Column(name = "posted_by")
    private String postedBy;

    @Column(name = "posted_at")
    private Date postedAt;

    @PrePersist
    protected void onCreate() {
        postedAt = new Date();
    }

    public Reply(String reply, Long postId) {
        this.reply = reply;
        this.postId = postId;
    }

    public Reply(Long postId, String reply, String postedBy) {
        this.reply = reply;
        this.postedBy = postedBy;
        this.postId = postId;
    }

}
