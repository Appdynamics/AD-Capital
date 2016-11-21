package com.appdynamics.loan.processor;

import com.appdynamics.loan.model.Applications;
import com.appdynamics.loan.service.ApplicationsService;
import com.appdynamics.loan.util.SpringContext;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.concurrent.TimeoutException;

/**
 * Created by amod.gupta on 7/28/15.
 */
@WebServlet(name = "Underwrite", urlPatterns = {"/Underwrite"})
public class Underwrite extends HttpServlet {

    private int customerid;
    private String applicationid;

    private static final Logger log = Logger.getLogger(Underwrite.class.getName());

    public static ApplicationsService getApplicationsService() {
        return (ApplicationsService) SpringContext.getBean("applicationsService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get application for Underwriting
        boolean found = getApplicationForUnderwriting();

        String message = "No application found for underwriting";
        if (found) {
            message = "Customer ID:" + customerid + ", your application has been sent to the underwriter";
            int num = (int)(Math.random()*99 + 1);
            if (num > 10)
                try {
                    sendToUnderwriter(this.applicationid);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
        }

        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        out.println(message);
        out.flush();

    }

    private boolean sendToUnderwriter(String applicationid) throws TimeoutException, IOException {
        boolean approved = true;

        ConnectionFactory factory = new ConnectionFactory();
        try {

            URI uri = new URI(GetRabbitMQURLFromConfigFiles());
            factory.setUri(uri);

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String queueName = "ApprovedAppsQueue";

            // Create queue if it doesn't exist
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, applicationid.getBytes());
            log.info(" [x] Sent '" + applicationid + "'");

            channel.close();
            connection.close();
        } catch (Exception ex) {
            log.error("Error Submitting Application" + ex.getMessage());
        }

        return approved;
    }

    private void deleteApplication(String applicationid)
    {
        try {
            getApplicationsService().deleteApplicationsByID(applicationid);
            log.info("Underwriter processed application with id - " + this.applicationid);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            StringWriter writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            e.printStackTrace(pw);
            log.error(writer.toString());
        }
    }

    private boolean getApplicationForUnderwriting() {
        boolean found = false;
        try {
            Applications applications = getApplicationsService().getApplicationsWithUWStatus();
            if (applications != null) {
                found = true;
                this.customerid = applications.getCustomerId();
                this.applicationid = String.valueOf(applications.getId().toLowerCase());

            } else {
                log.info("Application not found - UnderWriter");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if(found)
            deleteApplication(this.applicationid);

        return found;

    }

    private String GetRabbitMQURLFromConfigFiles() {
        GetConfigProperties properties = new GetConfigProperties();
        try {
            return properties.getRabbitMQUrl();
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            log.error(ex.getMessage());
        }
        return null;
    }
}
