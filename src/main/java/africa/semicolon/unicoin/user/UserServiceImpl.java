package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.exceptions.GenericHandlerException;
import africa.semicolon.unicoin.registration.token.ConfirmationToken;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenService;
import africa.semicolon.unicoin.user.dto.request.LoginRequest;
import africa.semicolon.unicoin.user.dto.response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public String createAccount(User user) {
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User foundUser = userRepository.findByEmailAddressIgnoreCase(loginRequest.getEmailAddress())
                .orElseThrow(()->new GenericHandlerException("User with " + loginRequest.getEmailAddress() +
                        " does not exist"));
        LoginResponse loginResponse = new LoginResponse();
        if (!foundUser.getPassword().equals(loginRequest.getPassword()))loginResponse.setMessage("Login incorrect");
        if (foundUser.getPassword().equals(loginRequest.getPassword())) loginResponse.setMessage("login successful");
        return loginResponse;

    }

    @Override
    public void enableUser(String email) {
        userRepository.enable(email);
    }
}
