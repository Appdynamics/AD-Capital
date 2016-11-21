package com.appdynamics.loan.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * Created by swetha.ravichandran on 8/17/15.
 */
@XmlRootElement(name = "applications")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Applications")
@Table(name = "applications")
public class Applications implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "applicationid")
    private String id = null;
    @Column(name = "loantype")
    private String loanType = null;
    @Column(name = "amount")
    private Integer amount = null;
    @Column(name = "customerid")
    private Integer customerId = null;
    @Column(name = "applicationstatus")
    private String applicationStatus = null;

    /**
     * Getter and Setter of id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter and Setter of loan type
     */
    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    /**
     * Getter and Setter of amount
     */
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Getter and Setter of customer id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter and Setter of application status
     */
    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
