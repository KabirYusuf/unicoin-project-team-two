package africa.semicolon.unicoin.registration.dto.requests;

import africa.semicolon.unicoin.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegistrationRequest {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String password;

}
