package africa.semicolon.unicoin.user.resetPassword.token.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {

    private String password;
    private String confirmPassword;
}
