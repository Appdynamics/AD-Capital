package com.appdynamics.loan.common;

import java.util.UUID;
import java.util.Date;
import java.io.Serializable;

/**
 * Created by amod.gupta on 7/24/15.
 */
public class LoanApplication implements Serializable {

    private String applicantName;
    private int applicantId;
    private String loanType;
    private int loanAmount;
    private UUID applicationId;
    private Date createDate;

    public LoanApplication(String username, int userid, String loantype, int amount) {

        setApplicantName(username);
        setApplicantId(userid);
        setLoanType(loantype);
        setLoanAmount(amount);

        setApplicationId();
        setCreateDate();

    }

    public String getApplicantName() {
        return applicantName;
    }

    private void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public int getApplicantId() {
        return applicantId;
    }

    private void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public String getLoanType() {
        return loanType;
    }

    private void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    private void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public UUID getApplicationId() {
        return applicationId;
    }

    private void setApplicationId() {

        this.applicationId = UUID.randomUUID();
    }

    public Date getCreateDate() {
        return createDate;
    }

    private void setCreateDate() {
        this.createDate = new Date();
    }
}
