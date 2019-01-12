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
public class ObjectQueryTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    public void testQuery(){
        Customer customer = customerDao.getOne(1L);
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan : linkMans) {
            System.out.println(linkMan);
        }
    }

    @Test
    @Transactional//开启事务
    public void testfindAll() {//
        Customer customer = customerDao.findOne(1L);
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan : linkMans) {
            System.out.println(linkMan);
        }
    }
}
