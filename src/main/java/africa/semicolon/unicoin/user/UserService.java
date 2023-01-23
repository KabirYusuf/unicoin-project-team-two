package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.user.dto.request.ChangePasswordRequest;
import africa.semicolon.unicoin.user.dto.request.DeleteRequest;
import africa.semicolon.unicoin.user.dto.request.LoginRequest;
import africa.semicolon.unicoin.user.dto.response.ChangePasswordResponse;

import java.util.Optional;

public interface UserService {
    String createAccount(User user);
    String login(LoginRequest loginRequest);

    void enableUser(String email);
    void saveUser(User user);

    String deleteUserByEmailAddress(String email, DeleteRequest deleteRequest);

    Optional<User> findUserByEmailAddress(String email);

    ChangePasswordResponse changePassword(String emailAddress, ChangePasswordRequest changePasswordRequest);


}
