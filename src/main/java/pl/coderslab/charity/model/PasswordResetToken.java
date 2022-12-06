package pl.coderslab.charity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    public PasswordResetToken() {
    }
    public PasswordResetToken(String token, User user, Date expiryDate) {
        this.token = token;
        this.user = user;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(expiryDate);
        calendar.add(Calendar.HOUR_OF_DAY, EXPIRATION);
        this.expiryDate = calendar.getTime();
    }


}
