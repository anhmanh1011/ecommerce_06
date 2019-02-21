package app.test;

import app.bean.Product;
import app.bean.Supplier;
import app.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/configs/spring-datasource.xml",
        "file:src/main/webapp/WEB-INF/configs/spring-datasource.xml",
        "file:src/main/webapp/WEB-INF/configs/spring-service.xml",
        "file:src/main/webapp/WEB-INF/configs/spring-dao.xml"
})
public class ProductServiceTest {

    @Autowired
    ProductService productService;


    @Test
    public void testPageCount() {
        int pageCount = productService.getPageCount();
        Assert.assertEquals(42, pageCount);
    }

    @Test
    public void getProduct() {
        Product product = productService.getProduct(1);
        Assert.assertNotNull(product);
    }

    @Test
    public void saveProduct() {
        Product product = new Product();
        product.setName("ASAMA 100");
        product.setMainPhoto("xedien2.jpge");
        product.setPrice(6000000);
        Supplier supplier = new Supplier();
        supplier.setId(1);
        product.setSupplier(supplier);
        List<Product> products = new ArrayList<>();
        products.add(product);
        boolean b = productService.saveProducts(products);
        Assert.assertTrue(b);
    }

    @Test
    public void getProductByNameAndSuppilerId() {
        List<Product> productByNameAndSuppilerId = productService.getProductByNameAndSuppilerId("b", 1);
        Assert.assertNotNull(productByNameAndSuppilerId);
    }


}
