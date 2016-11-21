package com.appdynamics.loan.rest;

import com.appdynamics.loan.model.Customer;
import com.appdynamics.loan.service.CustomerService;
import com.appdynamics.loan.util.SpringContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/service")
public class Service {
    private static final Logger log = Logger.getLogger(Service.class.getName());


    public CustomerService getCustomerService() {
        return (CustomerService) SpringContext.getBean("customerService");
    }

    @Autowired
    private ApplicationContext appContext;

    @POST
    @Path("/login")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public String ValidateUser(@Context HttpServletRequest req,
                               @FormParam("username") String name,
                               @FormParam("password") long passcode) throws Exception {
        boolean valid = false;
        try {
            log.info("name : " + name);
            log.info("password : " + passcode);
            valid = getCustomerService().validateMember(name, passcode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return valid ? "success" : "fail";
    }

    @GET
    @Path("/getcust")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerInformation(@Context HttpServletRequest req) throws Exception {
        String userName = req.getHeader("username");
        try {
            if (StringUtils.isNotBlank(userName)) {
                Customer customer = getCustomerService().getMemberByName(userName);
                return customer;
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}