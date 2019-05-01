package api.iti0208.data.output;

import api.iti0208.data.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDetails {
    private long id;
    private String reply;
    private String fileLocation;
    private Date postedAt;
    private String postedBy;
    private List<AppUser> upVoters;
    private boolean canDelete;
    private boolean canUpVote;
}
