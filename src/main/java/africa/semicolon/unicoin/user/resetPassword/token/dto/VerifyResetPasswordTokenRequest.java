package africa.semicolon.unicoin.user.resetPassword.token.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VerifyResetPasswordTokenRequest {

    String token;
}
