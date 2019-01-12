package cn.itcast.domain;

import javax.persistence.*;

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
