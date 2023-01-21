package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.user.dto.request.DeleteRequest;
import africa.semicolon.unicoin.user.dto.request.LoginRequest;

public interface UserService {
    String createAccount(User user);

    String login(LoginRequest loginRequest);

    void enableUser(String email);

    String deleteUserByEmailAddress(String email, DeleteRequest deleteRequest);
}
