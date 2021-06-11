package it.maurosaladino.sdp.Model;

import java.util.ArrayList;
import java.util.List;

public class Nodes {
    
    private List<Node> nodesList;

    // Sfrutto il singleton pattern
    private static Nodes instance;

    private Nodes() { this.nodesList = new ArrayList<>(); }

    // Utilizzando synchronized su un metodo statico applico un lock di classe
    // Synchronized perchè altrimenti si potrebbero creare 2 o più istanze contemporaneamento in fase iniziale
    public synchronized static Nodes getInstance() {
        if (instance == null) 
            instance = new Nodes();
        return instance;
    }

    /*
        1. Restituisco una shell copy di nodesList in quanto altrimenti restituirei un riferimento e altri metodi non sync
           potrebbero modificare la lista incorrendo in problemi di race condition
        2. Senza il synchronized ci potrebbero essere problemi di race condition in quanto per costruire l'ArrayList
           devo comunque iterare su nodesList
    */
    public synchronized List<Node> getNodesList() {
        return new ArrayList<>(nodesList);
    }


    public synchronized List<Node> add(Node n) {
        if (nodesList.stream().filter(t -> t.getID() == n.getID()).count() == 0) {
            nodesList.add(n);
            return new ArrayList<>(nodesList);
        }
        return null;
    }

    public synchronized boolean removeNode(int idToRemove) {
        return nodesList.removeIf(n -> n.getID() == idToRemove);
    }


    // Sfrutto una sincronizzazione più fine chiedendo il lock solo in nodesCopy
    public int getSizeNodesList() {
        List<Node> nodesCopy = getNodesList();
        return nodesCopy.size();
    }
    
}
