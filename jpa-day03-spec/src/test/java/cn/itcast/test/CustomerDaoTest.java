package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSpec() {
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");//root : 查询的对象 ，参数是查询对象对应的属性名，
                Predicate predicate = cb.equal(custName, "周生文");//cb : 查询方式（equal:精准匹配...）
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    @Test
    public void testSpec2() {//多条件拼接
        Customer customer = customerDao.findOne(new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate p1 = cb.equal(custName, "哈哈");
                Predicate p2 = cb.equal(custIndustry, "娱乐");
                //cb.and (与的条件拼接多个条件)
                //cb.or  (或的方式拼接多个条件)
                Predicate and = cb.and(p1, p2);
                return and;
            }
        });
        System.out.println(customer);
    }

    @Test
    public void testSpec3() {//模糊查询

        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "%哈哈");
                return predicate;
            }
        };
        //排序查询
        Sort sort = new Sort(Sort.Direction.DESC, "custId");//根据custId 属性，倒叙排列
        List<Customer> customerList = customerDao.findAll(spec,sort);
        for (Customer customer1 : customerList) {
            System.out.println(customer1);
        }
    }

    @Test
    public void testSpec4(){//分页
        Specification spec = null;
        Pageable pageable = new PageRequest(0,3);
        Page page = customerDao.findAll(spec, pageable);
        System.out.println(page.getTotalPages());//总页数
        System.out.println(page.getTotalElements());//总条数
        System.out.println(page.getContent());//显示的数据集合列表
    }
}


/*
5 --- 6       3 --- 5
0     5       0     0
5     5       0     3
4     6       1     5
0     4       1     0
5     4       3     1
3     6       0     4
     */


