package Service;

import app.bean.ProductDetail;
import app.service.ProductDetailsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/configs/spring-datasource.xml",
        "file:src/main/webapp/WEB-INF/configs/spring-datasource.xml",
        "file:src/main/webapp/WEB-INF/configs/spring-service.xml",
        "file:src/main/webapp/WEB-INF/configs/spring-dao.xml"
})
public class ProductDetailServiceTest {

    @Autowired
    ProductDetailsService productDetailsService;

    @Test
    public void getProductDetailByProductId() {
        ProductDetail productDetailbyProductId = productDetailsService.getProductDetailbyProductId(2);
        Assert.assertNotNull(productDetailbyProductId);
    }


}
