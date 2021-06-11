package it.maurosaladino.sdp.Model;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import it.maurosaladino.sdp.Proto.P2PServiceGrpc;
import it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest;
import it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse;
import it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete;
import it.maurosaladino.sdp.Proto.TokenServiceGrpc;
import it.maurosaladino.sdp.Proto.TokenServiceOuterClass.TokenRequest;
import it.maurosaladino.sdp.Proto.TokenServiceOuterClass.TokenResponse;
import java.io.IOException;


public class NodeServer {
    private Node node;

    public Server getServer() {
        return server;
    }

    private Server server;

    public NodeServer(Node n) {
        this.node = n;
    }

    public boolean tryToStartServer() {
        this.server = ServerBuilder
                .forPort(node.getListeningPort())
                .addService(new P2PServiceServer())
                .addService(new TokenServiceServer())
                .build();

        try {
            this.server.start();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void shutdownServer() {
        this.node.getPm10Sim().stopMeGently();
        this.server.shutdownNow();
    }


    class P2PServiceServer extends P2PServiceGrpc.P2PServiceImplBase {

        // RPC notifyNext(NotifyRequest) returns (NotifyResponse)
        // Vengo avvisato dal mio presunto predecessore
        @Override
        public void notifyNext(NotifyRequest request, StreamObserver<NotifyResponse> responseObserver) {
            // n è il nodo che mi contatta
            Node n = new Node();
            n.setID(request.getId());
            n.setIP(request.getIp());
            n.setListeningPort(request.getListeningPort());

            /*
            La mia politica lato server prevede che quando vengo avvisato dal mio presunto predecessore X,
            aggiungo tale nodo X alla mia lista e ricalcolo il mio predecessore.
            */
            synchronized (node) {
                    node.addNodeToList(n);

                    int myIndex = node.findMyIndex();
                    int previousIndex = myIndex - 1 >= 0 ? myIndex - 1 : node.getNodeList().size() - 1;
                    node.setSx(node.getNodeList().get(previousIndex));

                    NotifyResponse response = NotifyResponse.newBuilder().setStatus(NotifyResponse.Status.OK).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
            }
        }


        // RPC notifyPrevious(NotifyRequest) returns (NotifyResponse)
        //  Vengo avvisato dal mio presunto successore
        @Override
        public void notifyPrevious(NotifyRequest request, StreamObserver<NotifyResponse> responseObserver) {
            // n è il nodo che mi contatta
            Node n = new Node();
            n.setID(request.getId());
            n.setIP(request.getIp());
            n.setListeningPort(request.getListeningPort());

            /*
                La mia politica lato server prevede che quando vengo avvisato dal mio presunto successore X,
                aggiungo tale nodo X alla mia lista e ricalcolo il mio successore.
             */
            synchronized (node) {
                    node.addNodeToList(n);

                    int myIndex = node.findMyIndex();
                    int nextIndex = myIndex + 1 <= node.getNodeList().size() - 1 ? myIndex + 1 : 0;
                    node.setDx(node.getNodeList().get(nextIndex));

                    // Se il campo create_token è true vuol dire che sono stato il primo ad entrare o
                    // che ad un certo punto sono rimasto solo. Per tale motivo dovrò essere io a far
                    // partire il token
                    if (node.getCreateToken()) {
                        node.getToken().start();
                        node.setCreateToken(false);
                    }

                    NotifyResponse response = NotifyResponse.newBuilder().setStatus(NotifyResponse.Status.OK).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
            }
        }




        // RPC notifyNextDelete(NotifyRequestDelete) returns (NotifyResponse)
        // Vengo avvisato dal mio presunto predecessore che si eliminerà
        @Override
        public void notifyNextDelete(NotifyRequestDelete request, StreamObserver<NotifyResponse> responseObserver) {
            // n1_id è l'id del nodo che devo cancellare
            int n1_id = request.getIdDelete();

            // n è il nodo che mi passa n1 (n predecessoe di n1) e che devo aggiungere
            Node n = new Node();
            n.setID(request.getIdAdd());
            n.setIP(request.getIpAdd());
            n.setListeningPort(request.getListeningPortAdd());

            // La mia politica lato server prevede che quando vengo avvisato dal mio presunto predecessore X della
            // sua eliminazione, rimuovo tale nodo X dalla mia lista, aggiungo il predecessore di X che mi passa
            // e ricalcolo il mio predecessore.
            synchronized (node) {
                    node.getNodeList().removeIf(x -> x.getID() == n1_id);
                    node.addNodeToList(n);

                    int myIndex = node.findMyIndex();
                    int previousIndex = myIndex - 1 >= 0 ? myIndex - 1 : node.getNodeList().size() - 1;

                    if (myIndex != previousIndex)
                        node.setSx(node.getNodeList().get(previousIndex));
                    else
                        node.setSx(node);

                    NotifyResponse response = NotifyResponse.newBuilder().setStatus(NotifyResponse.Status.OK).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
            }
        }

        // RPC notifyPreviousDelete(NotifyRequestDelete) returns (NotifyResponse)
        // Vengo avvisato dal mio presunto successore che si eliminerà
        @Override
        public void notifyPreviousDelete(NotifyRequestDelete request, StreamObserver<NotifyResponse> responseObserver) {
            // n1_id è l'id del nodo che devo cancellare
            int n1_id = request.getIdDelete();

            // n è il nodo che mi passa n1 (n successore di n1) e che devo aggiungere
            Node n = new Node();
            n.setID(request.getIdAdd());
            n.setIP(request.getIpAdd());
            n.setListeningPort(request.getListeningPortAdd());

            // La mia politica lato server prevede che quando vengo avvisato dal mio presunto successore X della
            // sua eliminazione, rimuovo tale nodo X dalla mia lista, aggiungo il successore di X che mi passa
            // e ricalcolo il mio successore.
            synchronized (node) {
                    node.getNodeList().removeIf(x -> x.getID() == n1_id);
                    node.addNodeToList(n);

                    int myIndex = node.findMyIndex();
                    int nextIndex = myIndex + 1 <= node.getNodeList().size() - 1 ? myIndex + 1 : 0;

                    if (myIndex != nextIndex)
                        node.setDx(node.getNodeList().get(nextIndex));
                    else
                        node.setDx(node);

                    NotifyResponse response = NotifyResponse.newBuilder().setStatus(NotifyResponse.Status.OK).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            }
    }

    class TokenServiceServer extends TokenServiceGrpc.TokenServiceImplBase {
        // RPC sendToken(TokenRequest) returns (TokenResponse);
        // Il mio predecessore mi invia il token
        @Override
        public void sendToken(TokenRequest tokenRequest, StreamObserver<TokenResponse> responseObserver) {
            TokenResponse tokenResponse = TokenResponse.newBuilder().setCheck(true).build();
            responseObserver.onNext(tokenResponse);
            responseObserver.onCompleted();

            // Sleep necessaria per non sovraccaricare la CPU
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            node.getToken()._continue(tokenRequest);
        }
    }
}

