package it.maurosaladino.sdp.Builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import it.maurosaladino.sdp.Model.Utils;
import it.maurosaladino.sdp.Gateway.Configuration;

public class NodeBuilder {

    private BufferedReader br;
    private final String msgID = "Identificatore: ";
    private final String msgListeningPort = "Listening port del nodo: ";
    private final String msgIP = "Ottengo l'indirizzo IP del nodo:";
    private final String msgIPGateway = "Ottengo l'indirizzo IP del Gateway:";
    private final String msgPortGateway = "Ottengo la porta del Gateway:";

    public NodeBuilder() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }


    public int getInputID() throws IOException {
        System.out.print(msgID);
        
        do {
            String value = br.readLine();

            if (value.equals("quit"))
                System.exit(0);
                
            else if (value.matches("[1-9]+[0-9]*"))
                return Integer.parseInt(value);           
            
            else
                System.out.print(msgID);
        } 
        while (true);
    }


    public String getInputIP() {
        System.out.print(msgIP);
        try 
        {
            String ip = Inet4Address.getLocalHost().getHostAddress();
            Utils.printGreen(" OK\n");
            return ip;
        } 
        catch (UnknownHostException e) {
            Utils.printRed("Non riesco a recuperare l'IP del nodo");
            System.exit(-1);
        }
        return null;
    }


    public int getInputListeningPort() throws IOException {
        System.out.print(msgListeningPort);

        do {
            String value = br.readLine();

            if (value.equals("quit"))
                System.exit(0);
                
            else if (value.matches("[0-9]+") && Integer.parseInt(value) >= 0 && Integer.parseInt(value) <= 65535)
                return Integer.parseInt(value);           
            
            else
                System.out.print(msgListeningPort);
        } 
        while (true);
    }

    
    public String getIPGateway() {
        System.out.print(msgIPGateway);
        String ipGateway = Configuration.GatewayIP;
        Utils.printGreen(" OK\n");
        return ipGateway;
    }


    public int getPortGateway() {
        System.out.print(msgPortGateway);
        int portGateway = Configuration.getPortGateway();
        Utils.printGreen(" OK\n");
        return portGateway;
    }


    public void instructions() {
        System.out.println("############################");
        System.out.println("#        ISTRUZIONI        #");
        System.out.println("############################\n");
        System.out.println("- Per terminare il setup o l'esecuzione digitare in qualsiasi momento quit");
        System.out.println("- Come identificatore del nodo sono ammessi solo numeri interi maggiori di 0 (zero)");
        System.out.println("- La listening port del nodo può essere compresa, salvo disponibilità, nel seguente intervallo: [0-65535]\n\n");
    }

    public void setup() {
        System.out.println("############################");
        System.out.println("#           SETUP          #");
        System.out.println("############################\n");
    }
}
