package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import pl.coderslab.charity.model.PasswordResetToken;
import pl.coderslab.charity.model.PasswordResetToken;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
//import pl.coderslab.charity.repository.PasswordTokenRepository;
import pl.coderslab.charity.repository.PasswordTokenRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
   private final PasswordTokenRepository passwordTokenRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, PasswordTokenRepository passwordTokenRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder;
        this.passwordTokenRepository = passwordTokenRepository;
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }
    public void delete(Long id) {
        userRepository.deleteUserById(id);
    }
    public void update(User user) {
        userRepository.save(user);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }
}
