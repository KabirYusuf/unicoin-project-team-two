package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.user.dto.request.DeleteRequest;
import africa.semicolon.unicoin.user.dto.request.LoginRequest;
import africa.semicolon.unicoin.user.dto.request.ResendTokenRequest;
import jakarta.mail.MessagingException;

public interface UserService {
    String createAccount(User user);

    String login(LoginRequest loginRequest);

    void enableUser(String email);

    String deleteUserByEmailAddress(String email, DeleteRequest deleteRequest);

    String resendConfirmationToken(ResendTokenRequest resendTokenRequest) throws MessagingException;
}
