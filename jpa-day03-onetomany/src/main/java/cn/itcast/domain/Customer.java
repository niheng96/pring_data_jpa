package cn.itcast.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  *  @Entity:申明实体类
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    /**
     * strategy 属性
     *      GenerationType.IDENTITY : 自增，MySQL（数据库底层支持自增）
     *      GenerationType.SEQUENCE : 序列，oracle（数据库底层支持序列）
     *      GenerationType.TABLE    : jpa提供的一种机制，通过数据库创建一张表的形式帮我们完成自增
     *      GenerationType.AUTO     : 由程序自动帮我们选择主键生成策略
     * @OneToMany (声明表关系)
     *      targetEntity 属性：对应的是从表实体类对象的字节码对象
     * @JoinColumn (配置外键)
     *      name 属性：外键字段名称
     *      referencedColumnName 属性:主表的主键名称
     */
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "cust_id")
    private Long custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_source")
    private String custSource;//来源
    @Column(name = "cust_industry")
    private String custIndustry;//行业
    @Column(name = "cust_level")
    private String custLevel;//级别
    @Column(name = "cust_address")
    private String custAddress;//地址
    @Column(name = "cust_phone")
    private String custPhone;//联系方式
//    @OneToMany(targetEntity = LinkMan.class)
//    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    /**
     *  放弃维护权，由对方表（实体类）来维护
     *  mappedBy : 配置参数是对方关系属性名称
     *  cascade = CascadeType.ALL  级联所有操作
     *  MERGE：修改 REMOVE：移除 PERSIST：保存
     *
     *  fetch：配置关联对象的加载方式
     *      FetchType.EAGER:    立即加载
     *      FetchType.LAZY :    延迟加载
     *
     */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<LinkMan> linkMans = new HashSet<LinkMan>();//外键集合

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
