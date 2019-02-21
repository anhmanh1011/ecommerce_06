package app.controller.client;

import app.Utils.ObjectMapperUtils;
import app.bean.Account;
import app.bean.Cart;
import app.bean.Order;
import app.bean.OrderDetail;
import app.service.OrderService;
import app.service.ProductDetailsService;
import app.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    private static final Logger logger = Logger.getLogger(OrderController.class);

    @Autowired
    ProductService productService;

    @Autowired
    ProductDetailsService productDetailsService;

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/orders/create", headers = {"Accept=text/xml, application/json"})
    @ResponseBody
    public boolean createOrder(Order order, HttpSession httpSession, Principal principal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error(bindingResult.getFieldError().getField());
        }
        List<Cart> carts = (List<Cart>) httpSession.getAttribute("cart");
        if (order == null || carts == null) {
            return false;
        }
        logger.info(order);
        if (principal != null) {
            Account account = new Account();
            account.setEmail(principal.getName());
            order.setAccountByUserId(account);
        }
        List<OrderDetail> orderDetails = ObjectMapperUtils.orderDetailsMap(carts);
        order.setOrderDetails(orderDetails);
        if (orderService.save(order) != null)
            return true;
        return false;
    }
}
