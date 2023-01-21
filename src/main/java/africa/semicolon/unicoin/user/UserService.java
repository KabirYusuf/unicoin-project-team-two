package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.user.dto.request.LoginRequest;
import africa.semicolon.unicoin.user.dto.response.LoginResponse;

public interface UserService {
    String createAccount(User user);

    String login(LoginRequest loginRequest);

    void enableUser(String email);

    String deleteUserByEmailAddress(String emailAddress);
}
