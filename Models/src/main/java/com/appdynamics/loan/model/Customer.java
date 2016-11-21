package com.appdynamics.loan.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by swetha.ravichandran on 8/13/15.
 */
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(name = "name")
    private String name = null;
    @Column(name = "shippingaddress")
    private String shippingAddress = null;
    @Column(name = "telephone")
    private String telephone = null;
    @Column(name = "level")
    private String level = null;
    @Column(name = "email")
    private String email = null;
    @Column(name = "creditscore")
    private Integer creditScore = null;

    /**
     * Getter and Setter of id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter and Setter of name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter and Setter of shippingAddress
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Getter and Setter of telephone
     */
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Getter and Setter of level
     */
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Getter and Setter of creditScore
     */
    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }
}
