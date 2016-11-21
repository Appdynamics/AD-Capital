package com.appdynamics.loan.queuereader;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;


/**
 * Created by amod.gupta on 7/30/15.
 */
public class RabbitMQConfig {
    private static final String QUEUE_NAME = "ApprovedAppsQueue";

    public static void main(String[] argv) throws Exception {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            InputStream inputStream = RabbitMQConfig.class.getResourceAsStream(propFileName);
            ConnectionFactory factory = new ConnectionFactory();

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                System.out.println("property file '" + propFileName + "' not found in the classpath");
            }

            String mqUrl = prop.getProperty("mqurl");

            System.out.println(mqUrl);
            URI uri = new URI(mqUrl);
            factory.setUri(uri);
            while (true) {
                com.rabbitmq.client.Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();

                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                Consumer consumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope,
                                               AMQP.BasicProperties properties, byte[] body) throws IOException {

                        String message = new String(body, "UTF-8").toLowerCase();
                        dowork(message);
                        System.out.println(" [x] Received '" + message + "'");
                    }
                };
                channel.basicConsume(QUEUE_NAME, true, consumer);
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException ie) {
                    System.out.println(ie.getMessage());
                }
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void dowork(String applicationid)
    {
        int sleepduration = (int)(Math.random()*1999 + 1);

        try {
            Thread.sleep(sleepduration);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }

        /*
        if (sleepduration < 300)
            verified = false;
        */

        System.out.println("Application ID: " + applicationid + " has been approved by AD Capital");
    }

}
