package africa.semicolon.unicoin.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResendTokenRequest {
    private String emailAddress;
}
