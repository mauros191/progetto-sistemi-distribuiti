package it.maurosaladino.sdp.Model;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private List<Statistic> statisticsList;

    // Sfrutto il singleton pattern
    private static Statistics instance;

    private Statistics() { this.statisticsList = new ArrayList<>(); }

    // Utilizzando synchronized su un metodo statico applico un lock di classe
    // Synchronized perchè altrimenti si potrebbero creare 2 o più istanze contemporaneamento in fase iniziale
    public synchronized static Statistics getInstance() {
        if (instance == null)
            instance = new Statistics();
        return instance;
    }

    /*
        1. Restituisco una shell copy di statisticsList in quanto altrimenti restituirei un riferimento e altri metodi non sync
           potrebbero modificare la lista incorrendo in problemi di race condition
        2. Senza il synchronized ci potrebbero essere problemi di race condition in quanto per costruire l'ArrayList
           devo comunque iterare su statisticsList
    */
    public synchronized List<Statistic> getStatisticsList() {
        return new ArrayList<>(statisticsList);
    }

    public synchronized void add (Statistic s) {
        statisticsList.add(s);
    }

    public int getSizeStatisticsList() {
        List<Statistic> statisticsCopy = getStatisticsList();
        return statisticsCopy.size();
    }

    public List<Statistic> getLastStatistics(int n) {
        List<Statistic> statisticsCopy = getStatisticsList();
        int indexStarter = statisticsCopy.size() - n;

        if (indexStarter < 0)
            indexStarter = 0;

        List<Statistic> newStatisticsList = new ArrayList<>();

        for (int k = indexStarter; k < statisticsCopy.size(); k++)
            newStatisticsList.add(statisticsCopy.get(k));

        return newStatisticsList;
    }

    public AvgStatistic getLastAvgStatistics(int n) {
        return new AvgStatistic().buildAvgStatistic(getLastStatistics(n));
    }
}
