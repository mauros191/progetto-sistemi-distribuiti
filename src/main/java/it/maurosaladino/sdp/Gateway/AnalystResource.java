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


import java.util.List;

@Path("analysts")
public class AnalystResource {
    // Restituisco la lista degli analisti
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnalystsList() {
        return Response.ok(Analysts.getInstance().getAnalystList()).build();
    }

    // Aggiungi un analista di ID X solo se non è già presente in lista un altro analista avente ID X
    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAnalyst(Analyst n) {
        boolean listAnalyst = Analysts.getInstance().add(n);
        if (listAnalyst == true) {
            return Response.status(Status.CREATED).build();
        }
        return Response.status(Status.CONFLICT).build();
    }


    // Rimuovi un analista
    @Path("remove/{id}")
    @DELETE
    public Response removeAnalyst(@PathParam("id") int idAnalyst) {
        if (Analysts.getInstance().removeAnalyst(idAnalyst))
            return Response.noContent().build();

        return Response.status(Status.NOT_FOUND).build();
    }

}
