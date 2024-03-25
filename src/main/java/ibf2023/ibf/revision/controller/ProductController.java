package ibf2023.ibf.revision.controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2023.ibf.revision.model.Login;
import ibf2023.ibf.revision.model.Product;
import ibf2023.ibf.revision.service.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get listing page
    @GetMapping
    public ModelAndView listAllTodos(
        // @RequestParam(name = "status", defaultValue = "ALL") String status,
        HttpSession session) {

        ModelAndView mav = new ModelAndView("productlist");
        Login sessionLogin = (Login) session.getAttribute("login");
        String sessionGender = sessionLogin.getGender();

        List<Product> filteredProductList = new LinkedList<>();

        if (session.getAttribute("login") != null) {
            List<Product> productList = productService.getProductList();
            for (Product product : productList) {
                if (sessionGender.equals("F")) {
                    if (product.getCategory().equals("fragrances") || product.getCategory().equals("groceries")) {
                        filteredProductList.add(product);
                    }
                } else {
                    if (!product.getCategory().equals("fragrances")) {
                        filteredProductList.add(product);
                    }
                }
            }

            mav.addObject("showProductList", true); // Show the todo list since user is logged in
            mav.addObject("productList", filteredProductList);
        } else {
            mav.setViewName("redirect:/");
        }
        return mav;
    }

    @GetMapping("/p/buy/{id}")
    public String buyProduct(
        @PathVariable("id") Integer id,
        Model model) throws IOException {
        Product product = productService.getProductById(id);

        // System.out.println(product);

        if (product != null && product.getStock() > 0) {
            int currentStock = product.getStock();
            product.setStock(currentStock - 1); // Decrease stock by 1
            int currentBuyCount = product.getBuyCount();
            product.setBuyCount(currentBuyCount + 1); // Increase buy count by 1
            productService.updateProduct(product);
        } else {
            model.addAttribute("noStock", "Stock not enough");
        }

        List<Product> productList = productService.getProductList();

        model.addAttribute("productList", productList);
        return "redirect:/product";
    }
}
// Product [id=12, title=Brown Perfume, description=Royal_Mirage Sport Brown Perfume for Men & Women - 120ml, price=40, discountPercentage=15.66, rating=4.0, stock=52, brand=Royal_Mirage, category=fragrances, dated=Tue Mar 12 09:14:21 SGT 2024]