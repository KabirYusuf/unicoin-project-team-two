package africa.semicolon.unicoin.user.resetPassword.token;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {

    Optional<ResetPasswordToken>findByToken(String token);

    void deleteResetPasswordTokenByExpiresAtBefore(LocalDateTime currentTime);

    @Modifying
    @Query("UPDATE ResetPasswordToken resetPasswordToken " +
            "SET resetPasswordToken.resetAt=?1 " +
            "WHERE resetPasswordToken.token =?2")
    void setResetAt(LocalDateTime now, String token);
}
