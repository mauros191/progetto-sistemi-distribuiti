package it.maurosaladino.sdp.RestCall;

import it.maurosaladino.sdp.Gateway.Configuration;
import it.maurosaladino.sdp.Model.Node;
import it.maurosaladino.sdp.Model.Statistic;
import it.maurosaladino.sdp.Model.Utils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class NodeRestCall {

    private static Client client = ClientBuilder.newClient();


    // Richiesta DELETE all'endpoint /sdplab/api/nodes/remove/{id} per rimuoversi dal Gateway
    public static boolean sendRemoveReq(int id) {
        WebTarget target = client.target(Configuration.getUrlEndpoint() + "/sdplab/api/nodes/remove/" + id);

        Invocation.Builder builder = target.request();
        Response response = null;

        try {
            response = builder.delete();
        } catch (ProcessingException e) {
            Utils.printRed("Server non raggiungibile. Uscita in corso...");
            System.exit(-1);
        }

        int responseStatus = response.getStatus();

        if (responseStatus == Response.Status.NO_CONTENT.getStatusCode())
            return true;

        else if (responseStatus == Response.Status.NOT_FOUND.getStatusCode()) {
            return false;
        }

        Utils.printRed("Risposta del server sconosciuta: " + responseStatus);
        System.exit(-1);
        return false;
    }


    // Richiesta POST all'endpoint /sdplab/api/statistics/add per inviare una statistica della rete P2P al Gateway
    public static boolean sendStatistic(Statistic s) {
        ObjectMapper mapper = new ObjectMapper();
        WebTarget target = client.target(Configuration.getUrlEndpoint() + "/sdplab/api/statistics/add");
        String jsonString = new String();

        try {
            jsonString = mapper.writeValueAsString(s);
        } catch (IOException e) {
            Utils.printRed("Impossibile costruire il JSON dell'oggetto");
            System.exit(-1);
        }

        Invocation.Builder builder = target.request().accept(MediaType.APPLICATION_JSON);
        Response response = null;

        try {
            response = builder.post(Entity.entity(jsonString, MediaType.APPLICATION_JSON));
        } catch (ProcessingException e) {
            Utils.printRed("Server non raggiungibile");
        }

        int responseStatus = response.getStatus();

        if (responseStatus == Response.Status.OK.getStatusCode()) {
            return true;
        }
        return false;
    }


    // Richiesta POST all'endpoint /sdplab/api/nodes/add per ricevere la lista dei nodi presenti nella rete P2P
    public static List<Node> registerToGateway(Node n) {
        ObjectMapper mapper = new ObjectMapper();
        client = ClientBuilder.newClient();
        WebTarget target = client.target(Configuration.getUrlEndpoint() + "/sdplab/api/nodes/add");
        String jsonString = new String();

        try {
            jsonString = mapper.writeValueAsString(n);
        } catch (IOException e) {
            Utils.printRed("Impossibile costruire il JSON dell'oggetto");
            System.exit(-1);
        }

        Invocation.Builder builder = target.request().accept(MediaType.APPLICATION_JSON);
        Response response = null;

        try {
            response = builder.post(Entity.entity(jsonString, MediaType.APPLICATION_JSON));
        } catch (ProcessingException e) {
            Utils.printRed("Server non raggiungibile. Uscita in corso...");
            System.exit(-1);
        }

        int responseStatus = response.getStatus();
        String responseJSON = response.readEntity(String.class);

        if (responseStatus == Response.Status.CREATED.getStatusCode()) {
            System.out.print("Registrazione al gateway:");
            Utils.printGreen(" OK\n");

            try {
                Node[] asArray = mapper.readValue(responseJSON, Node[].class);
                return Arrays.asList(asArray);
            } catch (IOException e) {
                System.out.println("Impossibile leggere la lista dei nodi");
                System.exit(-1);
            }
        }
        else if (responseStatus == Response.Status.CONFLICT.getStatusCode()) {
            Utils.printRed("L'ID scelto risulta già occupato, si prega di inserirne uno nuovo");
            return null;
        }
        Utils.printRed("Risposta del server sconosciuta: " + responseStatus);
        System.exit(-1);
        return null;
    }
}

