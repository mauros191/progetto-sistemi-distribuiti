package it.maurosaladino.sdp.Model;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import it.maurosaladino.sdp.Proto.TokenServiceGrpc;
import it.maurosaladino.sdp.Proto.TokenServiceGrpc.TokenServiceBlockingStub;
import it.maurosaladino.sdp.Proto.TokenServiceOuterClass.TokenRequest;
import it.maurosaladino.sdp.RestCall.NodeRestCall;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Token extends Thread {
    private Node node;

    public Token(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        Utils.printGreen("E' stato fatto partire un nuovo token...\n");
        Set<Integer> listID = new HashSet<>();
        Set<Integer> listIDWithM = new HashSet<>();
        Map<Integer, Double> listMeasurement = new HashMap<>();
        boolean isOpen = true;

        // Aggiungo il mio ID alla lista
        listID.add(node.getID());

        synchronized (node) {
            Node nodeTosend = node.getDx();

            // Aggiungo il mio vicino destro alla lista
            listID.add(nodeTosend.getID());

            if (!node.statisticGlobalIsEmpty()) {
                listIDWithM.add(node.getID());
            }

            TokenRequest tokenRequest =
                    createTokenRequest(listID, listIDWithM, listMeasurement, isOpen);
            sendToken(tokenRequest, nodeTosend.getIP(), nodeTosend.getListeningPort(), nodeTosend.getID());
        }
    }

    public void _continue(TokenRequest tokenReceived) {
        Set<Integer> listID = new HashSet<>(tokenReceived.getListID().getValueList());
        Set<Integer> listIDWithM = new HashSet<>(tokenReceived.getListIDWithM().getValueList());
        Map<Integer, Double> listMeasurement = new HashMap<>(tokenReceived.getListMeasurement().getListMeasurementMap());
        boolean isOpen = tokenReceived.getIsOpen();
        Node nodeTosend;


        // LISTA APERTA
        // La lista è aperta, ancora non tutti hanno una misurazione
        if (isOpen) {
            synchronized (node) {
                // Aggiungo a listID il mio vicino destro
                listID.add(node.getDx().getID());

                if (!node.statisticGlobalIsEmpty()) {
                    listIDWithM.add(node.getID());
                }

                // Se i due set coincidono vuol dire che tutti i nodi hanno delle misurazioni -> CHIUDO LISTA
                if (listID.equals(listIDWithM)) {
                    isOpen = false;

                    // Inserisco la mia misurazione
                    listMeasurement.put(node.getID(), node.obtainStatisticGlobal().getValue());

                    // Se voglio uscire chiudo il server e dopo l'invio del token uscirò
                    if (node.isiWantToQuit()) {
                        node.callNeighborsForDelete();
                        node.getNodeServer().shutdownServer();
                        NodeRestCall.sendRemoveReq(node.getID());
                    }
                }

                // Indipendentemente dalla chiusura o meno della lista, giro il token avanti
                nodeTosend = node.getDx();
                TokenRequest tokenRequest =
                        createTokenRequest(listID, listIDWithM, listMeasurement, isOpen);
                sendToken(tokenRequest, nodeTosend.getIP(), nodeTosend.getListeningPort(), nodeTosend.getID());
            }
        }


        // LISTA CHIUSA
        // La lista è chiusa, tutti abbiamo almeno una misurazione e siamo pronti per mandare
        else if (!isOpen) {
            // Sono un nodo presente nella lista, la lista è chiusa e quindi sono certo di avere una misurazione
            if (listID.contains(node.getID())) {
                synchronized (node) {

                    // Inserisco la mia misurazione se non presente
                    if (!listMeasurement.containsKey(node.getID()))
                        listMeasurement.put(node.getID(), node.obtainStatisticGlobal().getValue());

                    // SONO RIMASTO SOLO
                    if (node.alone()) {
                        // Invio statistica al gateway
                        double avg = findAvg(listMeasurement);
                        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
                        Statistic s = new Statistic();
                        s.setValue(avg);
                        s.setTimestamp(timestamp);
                        NodeRestCall.sendStatistic(s);
                        Utils.printGreen("Statistica inviata!\n");

                        // Se voglio uscire, spengo il server ed esco
                        if (node.isiWantToQuit()) {
                            node.getNodeServer().shutdownServer();
                            NodeRestCall.sendRemoveReq(node.getID());
                        }

                        // Se non voglio uscire, esco dal thread token
                        else {
                            // Riassegno un nuovo token perchè qualora ci sia in futuro un altro ingresso, dal momento
                            // che sono rimasto solo, dovrò essere io a far ripartire il tutto e dato che il token
                            // è un thread potrei avere già consumato lo start (se in passato ho fatto partire un token)
                            Token t = new Token(node);
                            node.setToken(t);
                            node.setCreateToken(true);
                        }
                    }

                    // SE NON SONO RIMASTO SOLO
                    if (!node.alone()) {

                        // Voglio uscire
                        if (node.isiWantToQuit()) {
                            // Contatto i miei vicini
                            node.callNeighborsForDelete();

                            // Spengo il server e mi elimino
                            node.getNodeServer().shutdownServer();
                            NodeRestCall.sendRemoveReq(node.getID());

                            // Giro token
                            nodeTosend = node.getDx();
                            TokenRequest tokenRequest =
                                    createTokenRequest(listID, listIDWithM, listMeasurement, isOpen);
                            sendToken(tokenRequest, nodeTosend.getIP(), nodeTosend.getListeningPort(), nodeTosend.getID());
                        }

                        // Non voglio uscire
                        else if (!node.isiWantToQuit()) {
                            // Se tutti abbiamo messo la nostra misurazione: invio statistica e restart token
                            if (weAreReady(listID, listMeasurement)) {

                                // Invio statistica al gateway
                                double avg = findAvg(listMeasurement);
                                long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
                                Statistic s = new Statistic();
                                s.setValue(avg);
                                s.setTimestamp(timestamp);
                                NodeRestCall.sendStatistic(s);
                                Utils.printGreen("Statistica inviata!\n");

                                // Faccio ripartire il token
                                Token t = new Token(node);
                                node.setToken(t);
                                t.start();
                            }

                            // Qualcuno ancora deve inserire la misurazione, giro il token
                            else if (!weAreReady(listID, listMeasurement)){
                                nodeTosend = node.getDx();
                                TokenRequest tokenRequest =
                                        createTokenRequest(listID, listIDWithM, listMeasurement, isOpen);
                                sendToken(tokenRequest, nodeTosend.getIP(), nodeTosend.getListeningPort(), nodeTosend.getID());
                            }
                        }
                    }
                }
            }


            // SONO UN NODO APPENA INSERITO
            // Se sono un nodo nuovo non presente nella lista con lista chiusa che sta facendo solo il passaggio del token
            // ma che ha pronta una misurazione, allora inserisco il mio ID e la mia misurazione e giro il token.
            // In caso contrario passo semplicemente il token (se nel frattempo non sono rimasto solo perchè i nodi sono usciti)
            else if (!listID.contains(node.getID())) {
                synchronized (node) {

                    // Sono rimasto solo
                    if (node.alone()) {
                        // Se ho una statistica la inserisco
                        if (!node.statisticGlobalIsEmpty()) {
                            listID.add(node.getID());
                            listIDWithM.add(node.getID());
                            listMeasurement.put(node.getID(), node.obtainStatisticGlobal().getValue());
                        }

                        // Invio la statistica che mi hanno lasciato (contenente la mia o anche no)
                        double avg = findAvg(listMeasurement);
                        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
                        Statistic s = new Statistic();
                        s.setValue(avg);
                        s.setTimestamp(timestamp);
                        NodeRestCall.sendStatistic(s);
                        Utils.printGreen("Statistica inviata!\n");

                        // Riassegno un nuovo token
                        Token t = new Token(node);
                        node.setToken(t);

                        // Setto il createToken a true
                        node.setCreateToken(true);

                        // Voglio uscire -> chiudo tutto
                        if (node.isiWantToQuit()) {
                            node.getNodeServer().shutdownServer();
                            NodeRestCall.sendRemoveReq(node.getID());
                        }

                        // Non voglio uscire, si chiude il thread Token e ritorna l'invio della statistica locale automaticamente
                    }


                    // Non sono rimasto solo
                    else {
                        // Se ho una statistica la inserisco
                        if (!node.statisticGlobalIsEmpty()) {
                            listID.add(node.getID());
                            listIDWithM.add(node.getID());
                            listMeasurement.put(node.getID(), node.obtainStatisticGlobal().getValue());
                        }

                        // Giro il token avanti
                        nodeTosend = node.getDx();
                        TokenRequest tokenRequest =
                                createTokenRequest(listID, listIDWithM, listMeasurement, isOpen);
                        sendToken(tokenRequest, nodeTosend.getIP(), nodeTosend.getListeningPort(), nodeTosend.getID());
                    }
                }
            }
        }
    }


    private double findAvg(Map<Integer, Double> listMeasurement) {
        return (listMeasurement.entrySet().stream().mapToDouble(i -> i.getValue()).average().orElse(0));
    }


    // Se la lista con gli ID è uguale a quella delle misurazioni vuol dire che siamo pronti per mandare
    private boolean weAreReady(Set<Integer> listID, Map<Integer, Double> listMeasurement) {
        if (listID.equals(listMeasurement.keySet()))
            return true;
        return false;
    }


    private TokenRequest createTokenRequest(
            Set<Integer> listID,
            Set<Integer> listIDWithM,
            Map<Integer, Double> listMeasurement,
            boolean isOpen
    )
    {
        return TokenRequest.newBuilder()
                .setListID(TokenRequest.ListID.newBuilder().addAllValue(listID).build())
                .setListIDWithM(TokenRequest.ListIDWithM.newBuilder().addAllValue(listIDWithM).build())
                .setListMeasurement(TokenRequest.ListMeasurement.newBuilder().putAllListMeasurement(listMeasurement).build())
                .setIsOpen(isOpen)
                .build();
    }


    private void sendToken(TokenRequest tokenRequest, String ip, int listeningPort, int ID)
    {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(ip + ":" +listeningPort).usePlaintext().build();
        final TokenServiceBlockingStub stub = TokenServiceGrpc.newBlockingStub(channel);
        
        try {
            stub.sendToken(tokenRequest);
            // Chiudo il canale altrimenti ho delle eccezioni
            channel.shutdown();
        }
        catch (io.grpc.StatusRuntimeException e) {
            channel.shutdown();
        }
    }
}