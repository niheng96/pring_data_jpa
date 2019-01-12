package cn.itcast.test;

import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user = new User();
        Role role = new Role();
        user.setUserName("周生文");
        role.setRoleName("java程序员");
        user.getRoles().add(role);
        role.getUsers().add(user);
        userDao.save(user);
        roleDao.save(role);
    }


    @Test
    @Transactional
    @Rollback(false)
    public void testDelete(){
        userDao.delete(2L);
    }



}