package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.*;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class DonationController {
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final CategoryService categoryService;
    private final UserService userService;

    public DonationController(InstitutionService institutionService, DonationService donationService, CategoryService categoryService, UserService userService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/user/form")
    public String showForm(@AuthenticationPrincipal CurrentUser userSession, Model model) {
        Optional<User> user = userService.get(userSession.getUser().getId());
        if (user.isEmpty()) {
            return "login";
        }
        model.addAttribute("user", user.get());
        List<Institution> institutions = institutionService.getInstitutions();
        model.addAttribute("institutions", institutions);
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/user/form")
    public String saveForm(@AuthenticationPrincipal CurrentUser userSession, Model model, @Valid @ModelAttribute Donation donation, BindingResult result) {
        Optional<User> user = userService.get(userSession.getUser().getId());
        if (user.isEmpty()) {
            return "login";
        }
        model.addAttribute("user", user.get());
        if (result.hasErrors()) {
            return "form";
        }
        donationService.add(donation);
        return "form-confirmation";
    }

}
