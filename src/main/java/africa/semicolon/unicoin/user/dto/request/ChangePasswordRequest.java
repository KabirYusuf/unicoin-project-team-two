package africa.semicolon.unicoin.user.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    String email;
    String password;
    String newPassword;
}

