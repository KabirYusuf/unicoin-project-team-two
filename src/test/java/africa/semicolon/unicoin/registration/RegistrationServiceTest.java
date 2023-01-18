package africa.semicolon.unicoin.registration;

import africa.semicolon.unicoin.MockUtils;
import africa.semicolon.unicoin.registration.dto.requests.RegistrationRequest;
import africa.semicolon.unicoin.user.User;
import africa.semicolon.unicoin.user.UserService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;

import static africa.semicolon.unicoin.MockUtils.emailSenderMock;
import static africa.semicolon.unicoin.MockUtils.userRepositoryMock;
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

    @Test
    public void testRegister() throws MessagingException {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "kabir@gmail.com",
                "Kabir",
                "Yusuf",
                "1234"
        );


        doReturn("0793d5b5-eeec-4b82-a29b-ac5831c4153d")
                .when(userServiceMock).createAccount(any(User.class));

        String token = registrationService.register(registrationRequest);

        assertEquals(token, "0793d5b5-eeec-4b82-a29b-ac5831c4153d");

    }

}