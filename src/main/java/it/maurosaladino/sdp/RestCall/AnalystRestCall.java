package it.maurosaladino.sdp.RestCall;

import it.maurosaladino.sdp.Gateway.Configuration;
import it.maurosaladino.sdp.Model.*;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AnalystRestCall {

    private static NodeRestCall instance;
    private static Client client = ClientBuilder.newClient();

    public static int getSizeNodesList() {
        WebTarget target = client.target(Configuration.getUrlEndpoint() + "/sdplab/api/nodes/size");
        Invocation.Builder builder = target.request();
        Response response = null;

        try {
            response = builder.get();
        }
        catch (ProcessingException e) {
            Utils.printRed("Server non raggiungibile. Uscita in corso...");
            System.exit(-1);
        }

        int responseStatus = response.getStatus();
        String myResponse = response.readEntity(String.class);

        if (responseStatus == Response.Status.OK.getStatusCode()) {
            return Integer.parseInt(myResponse);
        }
        return -1;
    }


    public static Statistic[] getLastStatistics(String n) {
        ObjectMapper mapper = new ObjectMapper();
        WebTarget target = client.target(Configuration.getUrlEndpoint() + "/sdplab/api/statistics/last/" + n);

        Invocation.Builder builder = target.request();
        Response response = null;

        try {
            response = builder.get();
        }
        catch (ProcessingException e) {
            Utils.printRed("Server non raggiungibile. Uscita in corso...");
            System.exit(-1);
        }

        int responseStatus = response.getStatus();
        String responseJSON = response.readEntity(String.class);


        if (responseStatus == Response.Status.OK.getStatusCode()) {
            try {
                return mapper.readValue(responseJSON, Statistic[].class);
            } catch (IOException e) {
                return null;
            }
        }

        else if (responseStatus == Response.Status.NOT_FOUND.getStatusCode()) {
            return new Statistic[0];
        }
        else {
            Utils.printRed("Risposta del server sconosciuta: " + responseStatus);
            System.exit(-1);
        }
        return null;
    }


    public static AvgStatistic getAvgStatistics(String n) {
        ObjectMapper mapper = new ObjectMapper();
        WebTarget target = client.target(Configuration.getUrlEndpoint() + "/sdplab/api/statistics/avg/" + n);
        Invocation.Builder builder = target.request();
        Response response = null;

        try {
            response = builder.get();
        } catch (ProcessingException e) {
            Utils.printRed("Server non raggiungibile. Uscita in corso...");
            System.exit(-1);
        }

        int responseStatus = response.getStatus();
        String responseJSON = response.readEntity(String.class);

        if (responseStatus == Response.Status.OK.getStatusCode()) {
            try {
                return mapper.readValue(responseJSON, AvgStatistic.class);
            } catch (IOException e) {
                return null;
            }
        }
        else if (responseStatus == Response.Status.NOT_FOUND.getStatusCode()) {
            AvgStatistic a = new AvgStatistic();
            a.setAvg(-1);
            return a;
        }
        return null;
    }

    // Richiesta POST all'endpoint /sdplab/api/analysts/add
    public static boolean registerToGateway(Analyst a) {
        ObjectMapper mapper = new ObjectMapper();
        client = ClientBuilder.newClient();
        WebTarget target = client.target(Configuration.getUrlEndpoint() + "/sdplab/api/analysts/add");
        String jsonString = new String();

        try {
            jsonString = mapper.writeValueAsString(a);
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

        if (responseStatus == Response.Status.CREATED.getStatusCode()) {
            System.out.print("Registrazione al gateway:");
            Utils.printGreen(" OK\n");
            return true;
        }
        else if (responseStatus == Response.Status.CONFLICT.getStatusCode()) {
            Utils.printRed("L'ID scelto risulta già occupato, si prega di inserirne uno nuovo");
            return false;
        }
        else {
            Utils.printRed("Risposta del server sconosciuta: " + responseStatus);
            System.exit(-1);
            return false;
        }
    }

    // Richiesta DELETE all'endpoint /sdplab/api/analyst/remove/{id} per rimuoversi dal Gateway
    public static boolean sendRemove(int id) {
        WebTarget target = client.target(Configuration.getUrlEndpoint() + "/sdplab/api/analysts/remove/" + id);
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
}
