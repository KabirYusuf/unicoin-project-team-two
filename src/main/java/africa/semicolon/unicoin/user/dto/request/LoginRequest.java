package africa.semicolon.unicoin.user.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String emailAddress;
    private String password;
}
