package ibf2023.ibf.revision.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ibf2023.ibf.revision.model.Login;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class LoginController {
    
    @GetMapping
    public ModelAndView getLoginForm() {
        Login login = new Login();
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("newLogin", login);
        return mav;
    }

    @PostMapping
    public String login(
        HttpSession session,
        @ModelAttribute @Valid Login login,
        BindingResult result) {

        Date loginDate = new Date();
        login.setLoginDate(loginDate);

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "login";
        }

        session.setAttribute("login", login);

        return "redirect:/product";
    }
}
