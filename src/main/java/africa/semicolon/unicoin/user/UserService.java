package africa.semicolon.unicoin.user;

public interface UserService {
    String createAccount(User user);

    void enableUser(String email);
}
