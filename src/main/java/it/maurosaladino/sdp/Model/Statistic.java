package it.maurosaladino.sdp.Model;

public class Statistic {
    private double value;
    private long timestamp;

    public Statistic() { }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Value: " + this.value + "\nTimestamp: " + this.timestamp + "\n";
    }
}
