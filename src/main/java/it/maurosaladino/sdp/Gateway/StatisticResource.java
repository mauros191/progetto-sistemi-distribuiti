package it.maurosaladino.sdp.Gateway;

import it.maurosaladino.sdp.Model.Analyst;
import it.maurosaladino.sdp.Model.Analysts;
import it.maurosaladino.sdp.Model.Statistic;
import it.maurosaladino.sdp.Model.Statistics;
import it.maurosaladino.sdp.Proto.AnalystServiceOuterClass;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("statistics")
public class StatisticResource {

    @Path("last/{last_n}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastStatistics(@PathParam("last_n") int n) {
        if (Statistics.getInstance().getSizeStatisticsList() > 0)
            return Response.ok(Statistics.getInstance().getLastStatistics(n)).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStatistic(Statistic s) {
        Statistics.getInstance().add(s);

        List<Analyst> listAnalyst = Analysts.getInstance().getAnalystList();
        for (Analyst a : listAnalyst)
            AnalystPushNotification.push(a.getIP(), a.getListeningPort(),
                    AnalystServiceOuterClass.PushNotification.Type.NEW_STATISTIC);

        return Response.ok().build();
    }

    @Path("avg/{last_n}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvgLastStatistics(@PathParam("last_n") int n) {
        if (Statistics.getInstance().getSizeStatisticsList() > 0)
            return Response.ok(Statistics.getInstance().getLastAvgStatistics(n)).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
