package app.controller.client;

import app.bean.Product;
import app.bean.ProductDetail;
import app.service.ProductDetailsService;
import app.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    ServletContext context;

    @Autowired
    ProductService productService;
    @Autowired
    ProductDetailsService productDetailsService;


    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Product product = productService.getProduct(id);
        ProductDetail productDetail = productDetailsService.getProductDetailbyProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("productDetail", productDetail);
        return "detail";
    }

    @PostMapping("/search")
    public String search(String name, int suppiler, Model model) {
        List<Product> products = productService.getProductByNameAndSuppilerId(name, suppiler);
        model.addAttribute("products", products);
        model.addAttribute("pageCount", products.size() % 24 > 0 ? (products.size() / 24 + 1) : (products.size() / 24));
        model.addAttribute("pageNumber", 1);
        model.addAttribute("key", name);
        return "home";
    }
}
