package com.test.SpringSecturityTask.com.test.config.com.test.controller;

import com.test.SpringSecturityTask.com.test.config.com.test.model.User;
import com.test.SpringSecturityTask.com.test.config.com.test.model.UserForm;
import com.test.SpringSecturityTask.com.test.config.com.test.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Controller for new users registration
 */
@Controller
public class RegistrationController {

    private UserServiceImpl userService;

    /**
     * Public constructor
     *
     * @param userService - user service bean
     */
    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid UserForm userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "register";
        }
        else if (userService.findByUsername(userForm.getUsername()) != null) {

            model.addAttribute("exists", true);

            return "register";

        } else {



            userService.save(userForm);
        }

        return "redirect:/hello";
    }

    @GetMapping("/register")
    public String showRegisterPage(User user) {
        return "register";
    }
}
