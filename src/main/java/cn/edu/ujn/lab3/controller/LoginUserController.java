package cn.edu.ujn.lab3.controller;

import cn.edu.ujn.lab3.dao.UserMapper;
import cn.edu.ujn.lab3.model.ResultMSG;
import cn.edu.ujn.lab3.model.User;
import cn.edu.ujn.lab3.service.IUserService;
import cn.edu.ujn.lab3.utils.JWTUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:Joshua
 * @Date:2020/11/4
 */
@Controller
public class LoginUserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    private Gson gson = new Gson();


    public LoginUserController(IUserService userService/*,PasswordEncoder passwordEncoder*/) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "redirect:/pages/registration.html";
    }

    @PostMapping("/registerUser")
    @ResponseBody
    public ResultMSG processRegistration(@RequestBody String s) {
        System.out.println("s = " + s);
        User user = gson.fromJson(s, User.class);
        user.setUserState(1);
        System.out.println("user = " + user);

        if (userService.registerUser(user)) {
            ResultMSG resultMSG = ResultMSG.success();
            resultMSG.put("user", user);
            System.out.println(resultMSG);
            return resultMSG;
        }
        return ResultMSG.error(416, "注册失败!请检查用户名!");
    }

    @PostMapping("/findUsercode")
    @ResponseBody
    public ResultMSG findUsername(@RequestBody String data) {
        System.out.println("data = " + data);
        User user = gson.fromJson(data, User.class);
        String usercode = user.getUsercode();
        boolean byUserCode = userService.findByUserCode(usercode);
        if (byUserCode) {
            ResultMSG success = ResultMSG.success("账号可用!");
            return success;
        } else {
            return ResultMSG.error("账号不可用!");
        }
    }

    @GetMapping("/")
    public String initPage() {
        return "/login";
    }

    @GetMapping("/login")
    public String toLoginPage() {
        return "redirect:/pages/loginpage.html";
    }

    @PostMapping("/loginUser")
    @ResponseBody
    public ResultMSG loginUser(@RequestBody String userData, HttpServletResponse response) {
        System.out.println("userData = " + userData);
        User user = gson.fromJson(userData, User.class);
        System.out.println("user = " + user);

        if (userService.loginUser(user)) {
            //登陆成功,生成jwt令牌
            User byUsercode = userMapper.findByUsercode(user.getUsercode());
            String jwtToken = JWTUtils.genJWT(byUsercode);
            if (jwtToken == null) {
                return ResultMSG.error(416, "登录出错,请稍候再试!");
            }
            ResultMSG success = ResultMSG.success();
            Cookie usernameCookie = new Cookie("username", byUsercode.getUsername());
            usernameCookie.setPath("/");
            Cookie usercodeCookie = new Cookie("userid", String.valueOf(byUsercode.getUserId()));
            usercodeCookie.setPath("/");
            Cookie jwtTokenCookie = new Cookie("jwtToken", jwtToken);
            jwtTokenCookie.setPath("/");
            jwtTokenCookie.setHttpOnly(true);
            response.addCookie(usernameCookie);
            response.addCookie(usercodeCookie);
            response.addCookie(jwtTokenCookie);
            System.out.println("success = " + success);
            return success;
        }
        return ResultMSG.error(444, "账号密码错误,请检查后再试!");
    }

    @GetMapping("/main")
    public String toMainPage() {
        return "/pages/customer.html";
    }

    @RequestMapping("/error")
    public String errorPage() {
        return "redirect:/pages/error.html";
    }

    public void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setPath("/");
        cookie.setHttpOnly(true);

    }

}
