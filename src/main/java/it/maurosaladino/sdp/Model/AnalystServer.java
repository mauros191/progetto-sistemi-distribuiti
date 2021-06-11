package it.maurosaladino.sdp.Model;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import it.maurosaladino.sdp.Builder.AnalystBuilder;
import it.maurosaladino.sdp.Proto.AnalystServiceGrpc;
import it.maurosaladino.sdp.Proto.AnalystServiceOuterClass;
import java.io.IOException;

public class AnalystServer {
    private Analyst analyst;
    private Server server;

    public AnalystServer(Analyst a) {
        this.analyst = a;
    }

    public Server getServer() {
        return this.server;
    }

    public boolean tryToStartServer() {
        this.server = ServerBuilder
                .forPort(analyst.getListeningPort())
                .addService(new AnalystServiceServer())
                .build();
        try {
            this.server.start();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void shutdownServer() {
        this.server.shutdown();
    }

    class AnalystServiceServer extends AnalystServiceGrpc.AnalystServiceImplBase {
       // RPC sendPush(PushNotification) returns (PushResponse);
        @Override
        public void sendPush(AnalystServiceOuterClass.PushNotification request,
                             StreamObserver<AnalystServiceOuterClass.PushResponse> responseObserver) {

            AnalystServiceOuterClass.PushResponse response = AnalystServiceOuterClass.PushResponse.newBuilder()
                            .setStatus(AnalystServiceOuterClass.PushResponse.Status.OK)
                            .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            if (request.getType() == AnalystServiceOuterClass.PushNotification.Type.NEW_STATISTIC) {
                Utils.printGreen("\n\nNuova statistica presente nella rete\n\n");
                AnalystBuilder.menu();
            }

            else if (request.getType() == AnalystServiceOuterClass.PushNotification.Type.NEW_NODE) {
                Utils.printGreen("\n\nNuovo nodo presente nella rete\n\n");
                AnalystBuilder.menu();
            }

            else if (request.getType() == AnalystServiceOuterClass.PushNotification.Type.EXIT_NODE) {
                Utils.printGreen("\n\nUn nodo è uscito dalla rete\n\n");
                AnalystBuilder.menu();
            }
        }
    }


}
