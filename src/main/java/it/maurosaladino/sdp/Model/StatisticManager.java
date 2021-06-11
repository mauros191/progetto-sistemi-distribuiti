package it.maurosaladino.sdp.Model;

import it.maurosaladino.sdp.RestCall.NodeRestCall;
import it.unimi.simulator.Measurement;

public class StatisticManager {
    private Node node;

    public StatisticManager(Node node) {
        this.node = node;
    }

    public void addGlobalStatistic(Measurement global) {
        synchronized (node) {
            node.addStatisticGlobal(global);

            // getCreateToken perchè potrei avere ancora il token, sono rimasto solo
            // e ancora devo mandare la statistica di tutti i miei "ex" vicini e anche la mia.
            // Quando invece rimango solo e ho finito di gestire il token, la variabile
            // create_token ritorna a true
            if (node.alone() && node.getCreateToken()) {
                // Invio la media delle mie ultime statistiche che ho in coda
                sendLocalStatistic();
            }
        }
    }

    public void sendLocalStatistic() {
        Statistic s = node.obtainStatisticGlobal();
        if (s != null) {
            NodeRestCall.sendStatistic(s);
            System.out.print("Sono solo, invio la mia statistica locale: ");
            Utils.printGreen("OK\n");
        }
    }

}