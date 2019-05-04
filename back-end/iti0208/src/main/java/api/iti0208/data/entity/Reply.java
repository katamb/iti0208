package api.iti0208.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String reply;

    @Column(name = "file_location")
    private String fileLocation;

    @Column(name = "best_answer")
    @Builder.Default
    private boolean bestAnswer = false;

    @Column(name = "posted_at")
    private Date postedAt;

    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser postedBy;

    @PrePersist
    protected void onCreate() {
        postedAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public Reply(long id, String reply, AppUser postedBy) {
        this.id = id;
        this.reply = reply;
        this.postedBy = postedBy;
    }

    public Reply(String reply, Post post, AppUser postedBy) {
        this.reply = reply;
        this.post = post;
        this.postedBy = postedBy;
    }


}
