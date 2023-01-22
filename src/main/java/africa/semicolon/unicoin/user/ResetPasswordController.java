package africa.semicolon.unicoin.user;


import africa.semicolon.unicoin.user.resetPassword.token.ResetPasswordTokenService;
import africa.semicolon.unicoin.user.resetPassword.token.dto.EmailResetTokenRequest;
import africa.semicolon.unicoin.utils.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1/forgotPassword")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordTokenService resetPasswordTokenService;

    @PostMapping("/email-reset-token")
    public ResponseEntity<?> emailResetToken(@RequestBody EmailResetTokenRequest emailResetTokenRequest,
                                         HttpServletRequest httpServletRequest) throws MessagingException {
       String resetTokenResponse = resetPasswordTokenService.emailResetToken(emailResetTokenRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(resetTokenResponse)
                .isSuccessful(true).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

}