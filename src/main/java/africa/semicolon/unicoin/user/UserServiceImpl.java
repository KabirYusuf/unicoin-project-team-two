package africa.semicolon.unicoin.user;


import africa.semicolon.unicoin.email.EmailSender;
import africa.semicolon.unicoin.exceptions.GenericHandlerException;
import africa.semicolon.unicoin.registration.token.ConfirmationToken;
import africa.semicolon.unicoin.registration.token.ConfirmationTokenService;
import africa.semicolon.unicoin.user.dto.request.ChangePasswordRequest;
import africa.semicolon.unicoin.user.dto.request.DeleteRequest;
import africa.semicolon.unicoin.user.dto.request.LoginRequest;
import africa.semicolon.unicoin.user.dto.request.ResendTokenRequest;
import africa.semicolon.unicoin.user.dto.response.ChangePasswordResponse;
import africa.semicolon.unicoin.user.dto.response.LoginResponse;


import africa.semicolon.unicoin.utils.RandomStringGenerator;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public String createAccount(User user) {
        saveUser(user);
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

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User foundUser = userRepository.findByEmailAddressIgnoreCase(loginRequest.getEmailAddress())
                .orElseThrow(()->new GenericHandlerException("User with " + loginRequest.getEmailAddress() +
                        " does not exist"));
        LoginResponse loginResponse = new LoginResponse();
        if (!Objects.equals(foundUser.getPassword(), loginRequest.getPassword()))throw new GenericHandlerException("Login incorrect");


        return "Login successful";

    }

    @Override
    public void enableUser(String email) {
        userRepository.enable(email);
    }

    @Override
    public String deleteUserByEmailAddress(String email, DeleteRequest deleteRequest) {
        var foundUser = userRepository.findByEmailAddressIgnoreCase(email)
                .orElseThrow(()-> new GenericHandlerException("User with this"+ email +" does not exist"));
        if (!foundUser.getPassword().equals(deleteRequest.getPassword()))throw new GenericHandlerException("Incorrect password");
        StringBuilder randomValues = RandomStringGenerator.randomStringGenerator(8);
        foundUser.setEmailAddress("deleted" +email + randomValues);
        userRepository.save(foundUser);
        return "Deleted successfully";
    }


    @Override
    public Optional<User> findUserByEmailAddress(String email) {
        return userRepository.findByEmailAddressIgnoreCase(email);
    }

    @Override
    public ChangePasswordResponse changePassword(String emailAddress, ChangePasswordRequest changePasswordRequest) {
        var queriedUser = userRepository.findByEmailAddressIgnoreCase(emailAddress)
                .orElseThrow(() -> new GenericHandlerException("User with this email does not exist"));
        boolean isCorrectPassword = changePasswordRequest.getCurrentPassword().equals(queriedUser.getPassword());
        if (!isCorrectPassword) {
            throw new GenericHandlerException("Wrong Password");
        }
        if (!Objects.equals(changePasswordRequest.getNewPassword(), changePasswordRequest.getConfirmNewPassword()))throw new GenericHandlerException("" +
                "Password doesnt match");
        queriedUser.setPassword(changePasswordRequest.getNewPassword());
        userRepository.save(queriedUser);
        ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
        changePasswordResponse.setMessage("Password have been changed Successfully!");
        return changePasswordResponse;
    }

}



