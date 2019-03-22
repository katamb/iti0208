package api.iti0208.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5)
    private String reply;

    @NotNull
    private Long postId;

    private String fileLocation;

    private long userId;

    private String postedBy;

    private Date postedAt;

    @PrePersist
    protected void onCreate() {
        postedAt = new Date();
    }

    public Reply(String reply, Long postId) {
        this.reply = reply;
        this.postId = postId;
    }

}
