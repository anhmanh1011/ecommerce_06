package Service;

import app.bean.Order;
import app.bean.OrderDetail;
import app.dao.OrderDao;
import app.dao.OrderDetailDao;
import app.dao.ProductDao;
import app.model.AccountEntity;
import app.model.OrderEntity;
import app.service.impl.OrderServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/configs/spring-datasource.xml",
        "file:src/main/webapp/WEB-INF/configs/spring-datasource.xml",
        "file:src/main/webapp/WEB-INF/configs/spring-service.xml",
        "file:src/main/webapp/WEB-INF/configs/spring-dao.xml"
})
public class OrderServiceTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderDao orderDao;
    @Mock
    OrderDetailDao orderDetailDao;
    @Mock
    ProductDao productDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findOrderById() {
        when(orderService.findById(any(Integer.class))).thenReturn(new OrderEntity());
        Assert.assertNotNull(orderService.findById(1));

    }

    @Test
    public void getRevenue() {
        Assert.assertNotNull(orderService.getRevenue());
    }

    @Test
    public void update() {
        Order order = new Order();
        order.setStatus("Xác Nhận");
        order.setId(1);
        boolean update = orderService.update(order);
        Assert.assertTrue(update);
    }
}
