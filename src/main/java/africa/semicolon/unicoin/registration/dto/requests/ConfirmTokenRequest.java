package africa.semicolon.unicoin.registration.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConfirmTokenRequest {
    @NotNull
    private String token;
    private String email;
}
