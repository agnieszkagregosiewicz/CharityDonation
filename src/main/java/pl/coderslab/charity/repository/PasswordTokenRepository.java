package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.PasswordResetToken;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);

//    public void save(PasswordResetToken passwordResetToken) {
//        passwordResetToken.save();


    }




