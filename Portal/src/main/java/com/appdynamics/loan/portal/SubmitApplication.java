package com.appdynamics.loan.portal;

import com.appdynamics.loan.common.LoanApplication;
import com.appdynamics.loan.common.UserData;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Calendar;
import java.util.concurrent.TimeoutException;

/**
 * Created by amod.gupta on 7/24/15.
 */
@WebServlet(name = "SubmitApplication", urlPatterns = {"/SubmitApplication"})
public class SubmitApplication extends javax.servlet.http.HttpServlet {

    private static final Logger log = Logger.getLogger(SubmitApplication.class.getName());


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String code = request.getParameter("passcode");
        String loanType = request.getParameter("loantype");
        int passcode;

        UserData userdata = new UserData();

        if (username == null)
            username = userdata.name;

        if (code == null)
            passcode = userdata.passcode;
        else
            passcode = Integer.parseInt(code);

        if (loanType == null)
            loanType = generateLoanType();

        int amount = generateLoanAmount(loanType);
        String message = username + ", your application has been submitted. ID: " + amount + " " + loanType;

        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();


        // Create loan application object
        LoanApplication application = new LoanApplication(username, passcode, loanType, amount);
        message += application.getApplicationId();
        log.info(message);

        //Submit loan application object to queue
        try {
            submitLoanApplication(application);
        } catch (TimeoutException ex) {
            message = "Error Submitting Application" + ex.getMessage();
            log.error("Error Submitting Application" +ex.getMessage());
        }

        out.write(message);
        out.flush();

    }

    private void submitLoanApplication(LoanApplication application) throws TimeoutException, IOException {

        ConnectionFactory factory = new ConnectionFactory();
        try {

            String str_uri = "ec25-55-242-38-169.compute-1.amazonaws.com";

            if(!useExternalService())
                str_uri = GetRabbitMQURLFromConfigFiles();

            URI uri = new URI(str_uri);
            factory.setUri(uri);
            //factory.setHost("ec2-54-242-38-169.compute-1.amazonaws.com");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String queueName = "Applications";

            // Create queue if it doesn't exist
            channel.queueDeclare(queueName, false, false, false, null);
            byte[] message = SerializationUtils.serialize(application);
            channel.basicPublish("", queueName, null, message);
            log.info(" [x] Sent '" + application + "'");

            channel.close();
            connection.close();
        } catch (Exception ex) {
            log.error("Error Submitting Application" + ex.getMessage());
        }

    }

    private boolean useExternalService()
    {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        int minutes = calendar.get(calendar.MINUTE);

        if (minutes <= 5)
            flag = true;

        if (minutes >= 20 && minutes <=25)
            flag = true;


        if (minutes >= 40 && minutes <=45)
            flag = true;

        return flag;
    }

    private String generateLoanType() {
        String loanType;
        int number = (int) (Math.random() * 99 + 1);

        if (number <= 20)
            loanType = "Home";
        else if (number > 20 && number <= 50)
            loanType = "Car";
        else
            loanType = "Personal";

        return loanType;
    }

    private int generateLoanAmount(String type) {
        int amount = 0;

        if (type.equals("Personal"))
            amount = 1000 * (int) (Math.random() * 99 + 1);

        if (type.equals("Car"))
            amount = 1000 * (int) (Math.random() * 99 + 50);

        if (type.equals("Home"))
            amount = 1000 * (int) (Math.random() * 499 + 50);

        return amount;
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
