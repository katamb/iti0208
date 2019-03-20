package api.iti0208.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicUserInfo {
    private String username;

    private String firstName;

    private String lastName;

    private String email;
}
