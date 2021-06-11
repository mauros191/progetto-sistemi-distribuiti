package it.maurosaladino.sdp.Model;

import it.maurosaladino.sdp.RestCall.AnalystRestCall;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(
        {"ipGateway", "portGateway", "server"})
public class Analyst {

    private int ID;
    private String ip;
    private int listeningPort;
    private String ipGateway;
    private int portGateway;
    private AnalystServer server;

    public AnalystServer getServer() {
        return server;
    }

    public void setServer(AnalystServer server) {
        this.server = server;
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public int getListeningPort() {
        return listeningPort;
    }

    public void setListeningPort(int listeningPort) {
        this.listeningPort = listeningPort;
    }

    public String getIpGateway() {
        return ipGateway;
    }

    public void setIpGateway(String ipGateway) {
        this.ipGateway = ipGateway;
    }

    public int getPortGateway() {
        return portGateway;
    }

    public void setPortGateway(int portGateway) {
        this.portGateway = portGateway;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public synchronized boolean registerToGateway() {
        boolean register = AnalystRestCall.registerToGateway(this);
        if (register) {
            System.out.print("Inizializzazione: ");
            Utils.printGreen("OK\n");
            return true;
        }
        return false;
    }


}
