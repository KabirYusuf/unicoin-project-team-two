package africa.semicolon.unicoin.user.resetPassword.token;

import africa.semicolon.unicoin.user.resetPassword.ResetPasswordTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;



    @Configuration
    @EnableScheduling
    @RequiredArgsConstructor
    public class DeleteExpiredResetPasswordTokenScheduler {
        @Autowired
        private ResetPasswordTokenService resetPasswordTokenService;

        @Scheduled(cron = "0 0 0 * * *")
        public void deleteExpiredToken() {
            System.out.println("deleted");
           resetPasswordTokenService.deleteExpiredResetToken();
        }
    }

