package api.iti0208.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3)
    private String title;

    @NotNull
    private String topic;

    @NotNull
    @Size(min = 5)
    private String description;

    @Column(name = "reward_description")
    private String rewardDescription;

    @Column(name = "file_location")
    private String fileLocation;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "posted_at")
    private Date postedAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "posted_by")
    private String postedBy;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Reply> answers = new LinkedList<>();

    @PrePersist
    protected void onCreate() {
        postedAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public Post(String title, String description, String topic) {
        this.title = title;
        this.description = description;
        this.topic = topic;
    }

    public Post(Long id, String title, String description, String topic) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topic = topic;
    }

    public Post(Long id, String title, String description, String topic, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.userId = userId;
    }

    public Post(String title, String description, String topic, Long userId, String postedBy) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.userId = userId;
        this.postedBy = postedBy;
    }

    public Post(String title, String description, String topic, Long userId) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.userId = userId;
    }

    public Post(String topic, String title, String description, String rewardDescription, Long userId) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.rewardDescription = rewardDescription;
        this.userId = userId;
    }

    public Post(String topic, String title, String description, String rewardDescription,
                String fileLocation, Long userId) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.rewardDescription = rewardDescription;
        this.fileLocation = fileLocation;
        this.userId = userId;
    }

}
