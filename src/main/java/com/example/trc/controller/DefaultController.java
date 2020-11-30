package com.example.trc.controller;

import com.example.trc.model.users.User;
import com.example.trc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 07.10.2020 13:00
 */
@RestController
public class DefaultController {

    private final UserRepository userRepository;

    @Autowired
    public DefaultController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/greeting")
    public ModelAndView greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", userRepository.findByUsername(auth.getName()).get());
        return new ModelAndView("greeting");
    }

    @RequestMapping("/")
    public ModelAndView index(Model model) {
        Iterable<User> iterable = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (User user : iterable) {
            users.add(user);
        }
        Collections.sort(users, (User u1, User u2) -> u1.getId().compareTo(u2.getId()));
        model.addAttribute("userList", users);
        return new ModelAndView("index");
    }

    @GetMapping("/registration")
    public ModelAndView registration(Model model) {
        model.addAttribute("userForm");

        return new ModelAndView("registration");
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        User user = new User(
                userForm.getName(),
                userForm.getUsername(),
                encoder.encode(userForm.getPassword()),
                userForm.getAge(),
                userForm.getRole()
                );
        userRepository.save(user);
        return new ModelAndView("login");
    }
}
