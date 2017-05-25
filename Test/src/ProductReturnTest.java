

import java.math.BigDecimal;

/*import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bravowhale.business.pms.model.ProductReturn;
import com.bravowhale.business.pms.service.ProductReturnService;

import junit.framework.Assert;*/

/*@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/config/spring-*.xml"})*/
public class ProductReturnTest {
    /*@Autowired
    ProductReturnService productReturnService;
    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚 
    public void testSave(){
    	ProductReturn pr = new ProductReturn();
    	pr.setAmount1(new BigDecimal("123.0"));
    	pr.setArrTypeFk(10);
    	pr.setTerm1(1212);
    	pr.setProductFk(100L);
    	pr.setIsValid((byte)1);
    	int result = productReturnService.save(pr);
    	Assert.assertTrue(result > 0);
    }
*/
}
