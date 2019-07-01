package com.appdynamics.loan.processor;

import com.appdynamics.loan.model.Applications;
import com.appdynamics.loan.model.Customer;
import com.appdynamics.loan.service.ApplicationsService;
import com.appdynamics.loan.service.CustomerService;
import com.appdynamics.loan.util.SpringContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.UUID;

/**
 * Created by amod.gupta on 7/28/15.
 */
@javax.servlet.annotation.WebServlet(name = "CreditCheck", urlPatterns = {"/CreditCheck"})
public class CreditCheck extends javax.servlet.http.HttpServlet {

    final static int MAX_SCORE = 800;
    int customerid;
    String applicationid;
    int score;
    Applications currentApplication = null;

    private static final Logger log = Logger.getLogger(CreditCheck.class.getName());

    public static ApplicationsService getApplicationsService() {
        return (ApplicationsService) SpringContext.getBean("applicationsService");
    }

    public CustomerService getCustomerService() {
        return (CustomerService) SpringContext.getBean("customerService");
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        try {
            boolean found;
            boolean approve = false;
            String message = "No application found for credit approval";

            // Get Next Application for credit processing
            found = getApplicationForCreditCheck();

            if (found) {
                // Check FICO Score
                Customer currentCustomer = getFICOScore();

                // Decide
                if (score < 650)
                    approve  = true;

                double adjustedAmount = this.currentApplication.getAmount();

                // offer platinum members higher loan amounts based on the credit score
                if ( isPremiumCustomer(currentCustomer)){
                    long coeff = (MAX_SCORE-currentCustomer.getCreditScore())/MAX_SCORE;
                    double adjustment = this.currentApplication.getAmount()/coeff;
                    adjustedAmount += adjustment;
                }


                message = "Customer ID:" + customerid + " FICO Score: " + score + " Level: " + currentCustomer.getLevel() + " Approved: " + approve + " Proposed Amount: " + adjustedAmount;


                // Update Status
                updateApplicationStatus(approve);
            }

            response.setContentType("text/html");
            response.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter out = response.getWriter();
            log.info("Credit Check: " + message);

            out.println(message);
            out.flush();
        } catch (Exception e) {
            log.error(e.getMessage());
            StringWriter writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            e.printStackTrace(pw);
            log.error(writer.toString());
        }
    }



    private boolean isPremiumCustomer(Customer currentCustomer){
        return (currentCustomer != null &&
                (StringUtils.equals(currentCustomer.getLevel(), "Platinum")  || StringUtils.equals(currentCustomer.getLevel(), "Gold") ));
    }

    private boolean qualifiesForPromo(){
        int max = 10;
        Random random = new Random();
        int next = random.nextInt(max ) + 1;
        return next <= max/2 + max/5; //70%
    }

    private boolean updateApplicationStatus(boolean approve) {
        boolean success = false;
        int rows = 0;
        try {
            if (approve) {
                rows = getApplicationsService().updateApplicationsById(this.applicationid);
                log.info("Update Application Status for the application ID: " + this.applicationid);
            } else {
                rows = getApplicationsService().deleteApplicationsByID(this.applicationid);
                log.info("Delete Application Status for the application ID: " + this.applicationid);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            StringWriter writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            e.printStackTrace(pw);
            log.error(writer.toString());
        }
        if (rows == 1)
            success = true;
        return success;
    }

    private Customer getFICOScore() {
        Customer customer = null;
        try {
            customer = getCustomerService().getMemberById(this.customerid);
            if (customer != null) {
                this.score = customer.getCreditScore();
                log.info("Credit SCore: " + this.score);
            } else {
                log.info("No Customer found for the ID " + this.customerid);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            StringWriter writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            e.printStackTrace(pw);
            log.error(writer.toString());
        }
        return customer;
    }

    private boolean getApplicationForCreditCheck() {
        boolean found = false;
        try {
            Applications applications = getApplicationsService().getApplicationsWithCCStatus();
            if (applications != null) {
                this.currentApplication = applications;
                found = true;
                this.customerid = applications.getCustomerId();
                this.applicationid = applications.getId().toLowerCase();
                log.info("getApplicationForCreditCheck for Customer ID: " + this.customerid + " and Application ID:  " + this.applicationid);
            } else {
                log.info("No application found for Customer ID: " + this.customerid + " and Application ID:  " + this.applicationid);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            StringWriter writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            e.printStackTrace(pw);
            log.error(writer.toString());
        }
        return found;

    }


}
