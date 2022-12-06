package pl.coderslab.charity.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.PasswordResetToken;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.PasswordTokenRepository;
import java.util.Calendar;
import java.util.Locale;


@Service
@RequiredArgsConstructor
public class MailService{
    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

}
