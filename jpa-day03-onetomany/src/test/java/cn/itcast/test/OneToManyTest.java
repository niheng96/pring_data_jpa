package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个客户，保存一个联系人
     */

    @Test
    @Transactional//开启事务
    @Rollback(value = false)//设置不自动回滚
    public void testAdd() {
        Customer customer = new Customer();
        customer.setCustName("德玛西亚");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("光辉");
        customer.getLinkMans().add(linkMan);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional//开启事务
    @Rollback(value = false)//设置不自动回滚
    public void testAdd2() {//
        Customer customer = customerDao.findOne(1L);
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("花和尚");
        linkManDao.save(linkMan);
        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }



    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd3() {//级联操作

        Customer customer = new Customer();
        customer.setCustName("西游记");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("孙悟空");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testDelete() {//级联操作
        Customer customer = customerDao.findOne(3L);
        customerDao.delete(customer);
    }

}

