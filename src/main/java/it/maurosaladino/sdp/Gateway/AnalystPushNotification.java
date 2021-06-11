package it.maurosaladino.sdp.Gateway;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import it.maurosaladino.sdp.Proto.AnalystServiceGrpc;
import it.maurosaladino.sdp.Proto.AnalystServiceOuterClass;
import it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification;

public class AnalystPushNotification {
    public static void push(String ip, int port, PushNotification.Type p) {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(ip + ":" + port).usePlaintext().build();

        AnalystServiceGrpc.AnalystServiceStub stub = AnalystServiceGrpc.newStub(channel);
        PushNotification request = PushNotification.newBuilder().setType(p).build();

        stub.sendPush(request, new StreamObserver<AnalystServiceOuterClass.PushResponse>() {
            @Override
            public void onNext(AnalystServiceOuterClass.PushResponse pushResponse) {
                if (pushResponse.getStatus() == AnalystServiceOuterClass.PushResponse.Status.OK)
                    System.out.println("Notifica ricevuta correttamente");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Analista irraggiungibile");
            }

            @Override
            public void onCompleted() {
                System.out.println("Notifica inviata correttamente");
            }
        });

    }
}
