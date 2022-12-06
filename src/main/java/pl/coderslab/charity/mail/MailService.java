package pl.coderslab.charity.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.PasswordResetToken;
import pl.coderslab.charity.repository.PasswordTokenRepository;
import java.util.Calendar;




@Service
@RequiredArgsConstructor
public class MailService{
    private final JavaMailSender javaMailSender;
    private final PasswordTokenRepository passwordTokenRepository;


    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        //message.setFrom("noreplygregosiewicz@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

//    private SimpleMailMessage constructResetTokenEmail(
//            String contextPath, Locale locale, String token, User user) {
//        String url = contextPath + "/user/changePassword?token=" + token;
//        //String message = messages.getMessage("message.resetPassword",
//                null, locale);
//        return constructEmail("Reset Password", message + " \r\n" + url, user);
//    }

//    private SimpleMailMessage constructEmail(String subject, String body,
//                                             User user) {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject(subject);
//        email.setText(body);
//        email.setTo(user.getEmail());
//        email.setFrom(env.getProperty("support.email"));
//        return email;
//    }

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
