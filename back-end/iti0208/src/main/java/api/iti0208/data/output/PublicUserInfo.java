package api.iti0208.data.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicUserInfo {
    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private List<GrantedAuthority> grantedAuthorities;
}
