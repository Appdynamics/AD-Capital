package com.appdynamics.loan.portal;

import com.appdynamics.loan.common.UserData;
import com.appdynamics.loan.model.Customer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by swetha.ravichandran on 8/13/15.
 */
@WebServlet(name = "CustomerLogin", urlPatterns = {"/CustomerLogin"})
public class AuthenticationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AuthenticationServlet.class.getName());



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String code = request.getParameter("passcode");
        int passcode;

        UserData userdata = new UserData();

        if (username == null)
            username = userdata.name;

        if (code == null)
            passcode = userdata.passcode;
        else
            passcode = Integer.parseInt(code);



        log.info("UserName : " + username);

//        Client client = Client.create();
//        WebResource webResource = client.resource(GetOrderURLFromConfigFiles()
//                + "/login");
//
//        MultivaluedMap formData = new MultivaluedMapImpl();
//        formData.add("username", username);
//        formData.add("password", Integer.toString(passcode));
//        String returnResponse = webResource
//                .type("application/x-www-form-urlencoded")
//                .post(String.class, formData);
//        log.info("UserName : " + username + " ,PassCode: " + passcode);
//        if(returnResponse.equalsIgnoreCase("success")){
//            ClientConfig clientConfig = new DefaultClientConfig();
//            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
//                    Boolean.TRUE);
//            Customer customer = Client.create(clientConfig)
//                    .resource(GetOrderURLFromConfigFiles() + "/getcust")
//                    .accept(MediaType.APPLICATION_JSON)
//                    .header("username", username)
//                    .get(Customer.class);
//            log.info("Name : " + customer.getName() + "Level : " + customer.getLevel() + "Address : " + customer.getShippingAddress() + "CreditSCore : " + customer.getCreditScore() + "ID : " + customer.getId());
//            level = customer.getLevel();
//            userInfo += ", Level: " + level;
//            if(level.equalsIgnoreCase("platinum")) {
//                address = customer.getShippingAddress();
//                userInfo += ", Shipping Address: " + address;
//            }
//        }

        String userInfo = login(username, Integer.toString(passcode));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Current User: " + userInfo);
        log.info("Current User: " + userInfo);
        out.flush();
    }

    private String login(String username, String passcode)
    {
        String address = "";
        String level = "";
        String userInfo="";

        userInfo += username;
        Client client = Client.create();
        WebResource webResource = client.resource(GetOrderURLFromConfigFiles() + "/login");

        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("username", username);
        formData.add("password", passcode);

        String returnResponse = webResource
                .type("application/x-www-form-urlencoded")
                .post(String.class, formData);
        log.info("UserName : " + username + " ,PassCode: " + passcode);

        if(returnResponse.equalsIgnoreCase("success")){
            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
                    Boolean.TRUE);
            Customer customer = Client.create(clientConfig)
                    .resource(GetOrderURLFromConfigFiles() + "/getcust")
                    .accept(MediaType.APPLICATION_JSON)
                    .header("username", username)
                    .get(Customer.class);
            log.info("Name : " + customer.getName() + "Level : " + customer.getLevel() + "Address : " + customer.getShippingAddress() + "CreditSCore : " + customer.getCreditScore() + "ID : " + customer.getId());
            level = customer.getLevel();
            userInfo += ", Level: " + level;
            if(level.equalsIgnoreCase("platinum")) {
                address = customer.getShippingAddress();
                userInfo += ", Shipping Address: " + address;
            }
        }

        return userInfo;

    }

    private String GetOrderURLFromConfigFiles() {
        GetConfigProperties properties = new GetConfigProperties();
        try {
            return properties.getOrderUrl();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
