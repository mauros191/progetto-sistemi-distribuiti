package it.maurosaladino.sdp.Model;

import java.util.Arrays;
import it.unimi.simulator.Buffer;
import it.unimi.simulator.Measurement;

public class BufferPM10 implements Buffer {
    private final int sizeBuffer = 12;
    private final int overlap = 6;

    private Node node;
    private StatisticManager sm;
    private Measurement[] buffer = new Measurement[sizeBuffer];
    private int counter = 0;
    private int index = 0;

    public BufferPM10(Node n, StatisticManager sm) {
        this.node = n;
        this.sm = sm;
    }

    public StatisticManager getStatisticManager() {
        return this.sm;
    }

    @Override
    public void addMeasurement(Measurement m) {
        this.buffer[index++] = m;
        counter++;

        if (index == overlap && counter > sizeBuffer) {
            Measurement global = new Measurement(null, null, findAvg(), buffer[index-1].getTimestamp());
            sm.addGlobalStatistic(global);
        }
        else if (index == sizeBuffer) {
            Measurement global = new Measurement(null, null, findAvg(), buffer[index-1].getTimestamp());
            sm.addGlobalStatistic(global);
            index = 0;
        }
    }


    private double findAvg() {
        return Arrays.asList(buffer).stream().mapToDouble(Measurement::getValue).average().getAsDouble();
    }

}
