package it.maurosaladino.sdp.Gateway;

import java.io.IOException;
import java.util.Properties;

import it.maurosaladino.sdp.Model.Utils;

public class Configuration {
    public static final String GatewayIP = "localhost";
    private static int portGateway;

    public static int getPortGateway() {
        Properties props = new Properties();
        try {
            props.load(Configuration.class.getClassLoader().getResourceAsStream("project.properties"));
            Configuration.portGateway = Integer.parseInt(props.getProperty("portGateway"));
        } 
        catch (IOException e) {
            Utils.printRed("Errore nel recupero della porta");
            System.exit(-1);
        }
       
        return Configuration.portGateway;
    }

    public static String getUrlEndpoint() {
        return "http://" + GatewayIP + ":" + getPortGateway();
    }
}
