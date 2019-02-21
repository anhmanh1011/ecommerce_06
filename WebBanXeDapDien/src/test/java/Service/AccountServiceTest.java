package Service;

import app.bean.Account;
import app.service.AccountService;
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
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    public void createAccount(){
        boolean account = accountService.createAccount("doandinhlinh.kma@gmail.com");
        Assert.assertFalse(account);
    }

    @Test
    public void getAccount(){
        Account account = accountService.getAccount("doandinhlinh.kma@gmail.com");
        Assert.assertNotNull(account);
    }
}
