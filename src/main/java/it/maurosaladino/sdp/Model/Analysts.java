package it.maurosaladino.sdp.Model;

import java.util.ArrayList;
import java.util.List;

public class Analysts {

    private List<Analyst> analystList;

    // Sfrutto il singleton pattern
    private static Analysts instance;

    private Analysts() { this.analystList = new ArrayList<>(); }

    // Utilizzando synchronized su un metodo statico applico un lock di classe
    // Synchronized perchè altrimenti si potrebbero creare 2 o più istanze contemporaneamento in fase iniziale
    public synchronized static Analysts getInstance() {
        if (instance == null)
            instance = new Analysts();
        return instance;
    }

    /*
        1. Restituisco una shell copy di analysts in quanto altrimenti restituirei un riferimento e altri metodi non sync
           potrebbero modificare la lista incorrendo in problemi di race condition
        2. Senza il synchronized ci potrebbero essere problemi di race condition in quanto per costruire l'ArrayList
           devo comunque iterare su analystList
    */
    public synchronized List<Analyst> getAnalystList() {
        return new ArrayList<>(analystList);
    }


    public synchronized boolean add(Analyst a) {
        if (analystList.stream().filter(t -> t.getID() == a.getID()).count() == 0) {
            analystList.add(a);
            return true;
        }
        return false;
    }

    public synchronized boolean removeAnalyst(int idToRemove) {
        return analystList.removeIf(a -> a.getID() == idToRemove);
    }

}
