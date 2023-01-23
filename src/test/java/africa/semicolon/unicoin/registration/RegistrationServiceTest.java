package africa.semicolon.unicoin.registration;

import africa.semicolon.unicoin.MockUtils;
import africa.semicolon.unicoin.registration.dto.requests.ConfirmTokenRequest;
import africa.semicolon.unicoin.registration.dto.requests.RegistrationRequest;
import africa.semicolon.unicoin.registration.token.ConfirmationToken;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenRepository;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenService;
import africa.semicolon.unicoin.user.User;
import africa.semicolon.unicoin.user.UserRepository;
import africa.semicolon.unicoin.user.UserService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static africa.semicolon.unicoin.MockUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class RegistrationServiceTest {

    private final UserService userServiceMock = spy(MockUtils.userServiceMock());

    private RegistrationService registrationService = new RegistrationService(
            userRepositoryMock,
            userServiceMock,
            emailSenderMock,
            MockUtils.confirmationTokenServiceMock()

    );
    private  final ConfirmationTokenService confirmationTokenServiceMock =spy(MockUtils.confirmationTokenServiceMock());
    private  RegistrationRequest registrationRequest;

    @BeforeEach
    void setUp(){
        registrationRequest = new RegistrationRequest(
                "kabir@gmail.com",
                "Kabir",
                "Yusuf",
                "1234"
        );
    }
    @Test
    public void testRegister() throws MessagingException {

        doReturn("0793d5b5-eeec-4b82-a29b-ac5831c4153d")
                .when(userServiceMock).createAccount(any(User.class));

        String token = registrationService.register(registrationRequest);

        assertEquals(token, "0793d5b5-eeec-4b82-a29b-ac5831c4153d");

    }

    @Test
    public void testConfirmation() throws MessagingException {
        doReturn("12345")
                .when(userServiceMock).createAccount(any(User.class));
        String token = registrationService.register(registrationRequest);

        Optional<ConfirmationToken> confirmationToken = Optional.of(new ConfirmationToken());
        confirmationToken.get().setToken(token);
        confirmationToken.get().setCreatedAt(LocalDateTime.now());
        confirmationToken.get().setExpiredAt(LocalDateTime.now().plusMinutes(10));

        ConfirmTokenRequest confirmTokenRequest = new ConfirmTokenRequest(
                token,
                "kabir@gmail.com"
        );

        doReturn(confirmationToken).when(confirmationTokenRepositoryMock).findByToken(confirmTokenRequest.getToken());

        String response = registrationService.confirmToken(confirmTokenRequest);
        assertEquals("confirmed", response);
    }



}