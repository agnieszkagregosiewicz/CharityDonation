package pl.coderslab.charity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final pl.coderslab.charity.model.User user;
    public CurrentUser(String name, String surname, String email, String password, Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.charity.model.User user) {
        super(email, password, authorities);
        this.user = user;
    }


    public pl.coderslab.charity.model.User getUser() {
        return user;
    }
}
