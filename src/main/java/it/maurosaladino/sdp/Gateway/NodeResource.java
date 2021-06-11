package it.maurosaladino.sdp.Gateway;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.maurosaladino.sdp.Model.Analyst;
import it.maurosaladino.sdp.Model.Analysts;
import it.maurosaladino.sdp.Model.Node;
import it.maurosaladino.sdp.Model.Nodes;
import it.maurosaladino.sdp.Proto.AnalystServiceOuterClass;

import java.util.List;

@Path("nodes")
public class NodeResource {
    // Restituisco la lista dei nodi intera
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNodesList() {
        return Response.ok(Nodes.getInstance().getNodesList()).build();
    }

    // Aggiungi un nodo di ID X solo se non è già presente in lista un altro nodo avente ID X
    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNode(Node n) {
        List<Node> listNode = Nodes.getInstance().add(n);
        if (listNode != null) {
            List<Analyst> listAnalyst = Analysts.getInstance().getAnalystList();
            for (Analyst a : listAnalyst)
                AnalystPushNotification.push(a.getIP(), a.getListeningPort(),
                        AnalystServiceOuterClass.PushNotification.Type.NEW_NODE);

            return Response.status(Status.CREATED).entity(listNode).build();
        }
        return Response.status(Status.CONFLICT).build();
    }


    // Numero di nodi presenti nella rete
    @Path("size")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSizeNodesList() {
        return Response.ok(Nodes.getInstance().getSizeNodesList()).build();
    }


    // Rimuovi un nodo
    @Path("remove/{id}")
    @DELETE
    public Response removeNode(@PathParam("id") int idNode) {
        if (Nodes.getInstance().removeNode(idNode)) {
            List<Analyst> listAnalyst = Analysts.getInstance().getAnalystList();
            for (Analyst a : listAnalyst)
                AnalystPushNotification.push(a.getIP(), a.getListeningPort(),
                        AnalystServiceOuterClass.PushNotification.Type.EXIT_NODE);

            return Response.noContent().build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

}
