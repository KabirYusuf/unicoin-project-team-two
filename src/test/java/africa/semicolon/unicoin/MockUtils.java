package africa.semicolon.unicoin;

import africa.semicolon.unicoin.email.EmailSender;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenRepository;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenService;
import africa.semicolon.unicoin.user.UserRepository;
import africa.semicolon.unicoin.user.UserService;
import africa.semicolon.unicoin.user.UserServiceImpl;

import static org.mockito.Mockito.mock;

public class MockUtils {

    public static  final UserRepository userRepositoryMock = mock(UserRepository.class);

    public static final EmailSender emailSenderMock = mock(EmailSender.class);

    public static UserService userServiceMock(){
        return new UserServiceImpl(userRepositoryMock, confirmationTokenServiceMock());
    }

    public static ConfirmationTokenService confirmationTokenServiceMock(){
        return new ConfirmationTokenService();
    }
}
