package it.maurosaladino.sdp.Runner;

import it.maurosaladino.sdp.Builder.NodeBuilder;
import it.maurosaladino.sdp.Model.*;
import it.unimi.simulator.PM10Simulator;

import java.io.IOException;

public class NodeRunner {
    public static void main(String[] args) {
        NodeBuilder nb = new NodeBuilder();
        Node n = new Node();
        NodeServer ns = new NodeServer(n);
        n.setNodeServer(ns);

        nb.instructions();
        nb.setup();

        // Ottengo automaticamente IP del nodo e IP e porta del Gateway
        n.setIP(nb.getInputIP());
        n.setIpGateway(nb.getIPGateway());
        n.setPortGateway(nb.getPortGateway());

        // Faccio inserire all'utente un ID e la listening port
        try {
            n.setID(nb.getInputID());
            n.setListeningPort(nb.getInputListeningPort());

            // Se la listening port risulta già occupata la faccio reinserire
            while (!ns.tryToStartServer()) {
                Utils.printRed("La porta selezionata risulta già occupata o non disponibile");
                n.setListeningPort(nb.getInputListeningPort());
            }
        } 
        catch (Exception e) {
            Utils.printRed("Errore nella creazione dell'oggetto");
            System.exit(-1);
        }

        // Creo il token ma non lo avvio
        Token t = new Token(n);
        n.setToken(t);

        // Richiedo al Gateway di poter entrare nella rete P2P.
        // sendRegisterReq ritorna false se l'ID scelto è già presente nella rete P2P
        while (!n.registerToGateway()) {
            try {
                n.setID(nb.getInputID());
            }
            catch (IOException e) {
                Utils.printRed("Errore nella creazione dell'oggetto");
                System.exit(-1);
            }
        }

        // Configuro Statistic Manager e simulatore PM10
        StatisticManager sm = new StatisticManager(n);
        BufferPM10 b = new BufferPM10(n, sm);
        n.setPm10Sim(new PM10Simulator(b));
        n.getPm10Sim().start();

        // Avvio il thread che si occupa di gestire l'uscita tramite I/O
        HandlerInput h = new HandlerInput(n);
        h.start();

        // In questa modo il server terminerà solo quando riceverà un segnale esplicito di shutdown
        try {
            ns.getServer().awaitTermination();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
