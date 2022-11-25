package pl.coderslab.charity.controller;

import org.springframework.core.ResolvableType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, User user) {
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/admin/list")
    public String allUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users-list";
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
    public String submit(@Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            result.addError(new FieldError("user", "email", "Email już istnieje"));
            return "register";
        }
        userService.add(user);
        return "redirect:/";
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



