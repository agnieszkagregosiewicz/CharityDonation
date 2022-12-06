package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.mail.MailService;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.PasswordTokenRepository;
import pl.coderslab.charity.service.SecurityService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {

    private final UserService userService;
   private final MailService mailService;
   private final SecurityService securityService;
   private final PasswordTokenRepository passwordTokenRepository;

    public UserController(UserService userService, MailService mailService, SecurityService securityService, PasswordTokenRepository passwordTokenRepository) {
        this.userService = userService;
        this.mailService = mailService;
        this.securityService = securityService;
        this.passwordTokenRepository = passwordTokenRepository;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, User user) {
        model.addAttribute("user", user);
        return "login";
    }
    @GetMapping("/reset")
    public String getResetPage(Model model, User user) {
        model.addAttribute("user", user);
        return "passwordResetPage";
    }
    @PostMapping("/reset")
    public String getEmailToReset(Model model, @RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            model.addAttribute("error", "");
            return "redirect:reset?error";
        }
        String token = UUID.randomUUID().toString();
        LocalDate localDate = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        userService.createPasswordResetTokenForUser(user.get(), token, date);
        String text = "Kliknij w link, żeby zrestartować hasło";
        String textEnd = "Link jest ważny przez 24h.";
        String link = "http://localhost:8080/changePassword?token=" + token;;
        String content = text + "\n" + link + "\n" + textEnd;
        mailService.sendEmail(email, "Password reset Charity Donation", content);
       model.addAttribute("good", "");
        return "redirect:reset?good";
    }
    @GetMapping("/changePassword")
    public String showChangePasswordPage(Locale locale, Model model,
                                         @RequestParam("token") String token) {
        String result = securityService.validatePasswordResetToken(token);
        if(result != null) {
            return "redirect:/login?lang="
                    + locale.getLanguage() + "&message=" + result;
        } else {
            model.addAttribute("token", token);
            User user = passwordTokenRepository.findByToken(token).getUser();
            model.addAttribute("user", user);
            return "updatePassword";
        }
    }

    @PostMapping("/changePassword")
    public String savePassword(@RequestParam String password, @RequestParam String password2, @RequestParam("token") String token, Model model){
        if (!password2.equals(password)) {
            model.addAttribute("error");
            return "redirect:/changePassword?error=";
        }
        User user = passwordTokenRepository.findByToken(token).getUser();
        userService.changeUserPassword(user, password2);
        return "redirect:login?change=good";
    }
    @GetMapping("/user")
    public String readUser(@AuthenticationPrincipal CurrentUser userSession, Model model) {
        Optional<User> user = userService.get(userSession.getUser().getId());
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            return "login";
        }
        return "index";
    }

    @GetMapping(value = "/register")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    public String submit(@Valid @ModelAttribute User user, BindingResult result, @RequestParam String password2) {
        if (!password2.equals(user.getPassword())) {
            result.addError(new FieldError("user", "password", "Hasła muszą być identyczne."));
            return "register";
        }
        if (result.hasErrors()) {
            return "register";
        }
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            result.addError(new FieldError("user", "email", "Email już istnieje"));
            return "register";
        }
        userService.add(user);
        return "redirect:/user/form";
    }

    @GetMapping(value = "/user/delete")
    public String delete(User user) {
        userService.delete(user.getId());
        return "redirect:/logout";
    }

    @GetMapping(value = "/user/edit")
    public String editForm(User user, Model model) {
        model.addAttribute("user", userService.get(user.getId()).get());
        return "user-edit";
    }

    @PostMapping(value = "/user/edit")
    public String edit(@Valid User user, BindingResult result) {
        if (!result.hasErrors()) {
            userService.update(user);
            return "redirect:/user";
        }
        return "user-edit";
    }
}



