package cn.edu.ujn.lab3.controller;

import cn.edu.ujn.lab3.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:Joshua
 * @Date:2020/11/4
 */
@Controller
@RequestMapping("/register")
public class LoginUserController {
    private User user;
    //private PasswordEncoder passwordEncoder;

    public LoginUserController(User user/*,PasswordEncoder passwordEncoder*/) {
        this.user = user;
    }
}
