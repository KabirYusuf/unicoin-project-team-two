package africa.semicolon.unicoin.user;

import africa.semicolon.unicoin.email.EmailSender;
import africa.semicolon.unicoin.exceptions.GenericHandlerException;
import africa.semicolon.unicoin.registration.token.ConfirmationToken;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenService;
import africa.semicolon.unicoin.utils.RandomStringGenerator;
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
    public void enableUser(String email) {
        userRepository.enable(email);
    }

    @Override
    public String deleteUserByEmailAddress(String emailAddress) {
        var foundUser = userRepository.findByEmailAddressIgnoreCase(emailAddress)
                .orElseThrow(()-> new GenericHandlerException("User with this "+ emailAddress +" does not exist"));
        StringBuilder randomValues = RandomStringGenerator.randomStringGenerator(8);
        foundUser.setEmailAddress("deleted" +emailAddress + randomValues);
        userRepository.save(foundUser);
        return "Deleted successfully";
    }
}
