package it.maurosaladino.sdp.Runner;

import it.maurosaladino.sdp.Builder.AnalystBuilder;
import it.maurosaladino.sdp.Model.*;

import java.io.IOException;

public class AnalystRunner {
    public static void main(String[] args) {
        Analyst a = new Analyst();
        AnalystBuilder ab = new AnalystBuilder(a);
        AnalystServer as = new AnalystServer(a);
        a.setServer(as);

        AnalystBuilder.instructions();
        AnalystBuilder.setup();

        // Ottengo automaticamente IP dell'analista e IP e porta del Gateway
        a.setIP(ab.getInputIP());
        a.setIpGateway(ab.getIPGateway());
        a.setPortGateway(ab.getPortGateway());

        // Faccio inserire all'utente un ID e la listening port
        try {
            a.setID(ab.getInputID());
            a.setListeningPort(ab.getInputListeningPort());

            // Se la listening port risulta già occupata la faccio reinserire
            while (!as.tryToStartServer()) {
                Utils.printRed("La porta selezionata risulta già occupata o non disponibile");
                a.setListeningPort(ab.getInputListeningPort());
            }
        }
        catch (Exception e) {
            Utils.printRed("Errore nella creazione dell'oggetto");
            System.exit(-1);
        }

        // Richiedo al Gateway di poter registrarmi alla lista degli analisti
        // sendRegisterReq ritorna false se l'ID scelto è già presente nella lista degli analisti
        while (!a.registerToGateway()) {
            try {
                a.setID(ab.getInputID());
            }
            catch (IOException e) {
                Utils.printRed("Errore nella creazione dell'oggetto");
                System.exit(-1);
            }
        }

        try {
            ab.enterMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
