package ibf2023.ibf.revision.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ibf2023.ibf.revision.model.Login;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @GetMapping
    public ModelAndView getProductList(HttpSession session) {

        ModelAndView mav = new ModelAndView("productlist");

        String username = (String) session.getAttribute("username");
        String gender = (String) session.getAttribute("gender");
        String phoneNo = (String) session.getAttribute("phoneNo");

        Login login = new Login(username, gender, phoneNo, new Date());

        if (session.getAttribute("username") != null && session.getAttribute("gender") != null) {

            mav.addObject("showProductList", true); 
            mav.addObject("userDetails", login); 
            return mav;
        }

        return null;
    }
}
