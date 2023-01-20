package africa.semicolon.unicoin.registration;

import africa.semicolon.unicoin.registration.dto.requests.ConfirmTokenRequest;
import africa.semicolon.unicoin.registration.dto.requests.RegistrationRequest;
import africa.semicolon.unicoin.utils.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest, HttpServletRequest httpServletRequest) throws MessagingException{
        var createdUser =registrationService.register(registrationRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(createdUser)
                .isSuccessful(true).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @PostMapping("/confirm")
    public ResponseEntity<?>confirmToken(@RequestBody ConfirmTokenRequest confirmTokenRequest, HttpServletRequest httpServletRequest){
//        registrationService.confirmToken(confirmTokenRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(registrationService.confirmToken(confirmTokenRequest))
                .isSuccessful(true).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
