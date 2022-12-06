package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.PasswordResetToken;
import pl.coderslab.charity.repository.PasswordTokenRepository;

import java.util.Calendar;
@Service
public class SecurityService {
private final PasswordTokenRepository passwordTokenRepository;

    public SecurityService(PasswordTokenRepository passwordTokenRepository) {
        this.passwordTokenRepository = passwordTokenRepository;
    }

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
}
