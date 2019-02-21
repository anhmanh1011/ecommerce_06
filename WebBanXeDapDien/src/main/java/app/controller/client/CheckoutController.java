package app.controller.client;

import app.bean.Cart;

import java.security.Principal;
import java.util.List;

import app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CheckoutController {

    @Autowired
    AccountService accountService;

    @GetMapping("/checkout")
    public String checkout(HttpSession httpSession, Model model, Principal principal) {
        List<Cart> carts = (List<Cart>) httpSession.getAttribute("cart");
        model.addAttribute("carts", carts);
        if (principal != null)
            model.addAttribute("account", accountService.getAccount(principal.getName()));
        return "checkout";
    }
}
