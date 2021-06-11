package it.maurosaladino.sdp.Model;

import java.util.List;

public class AvgStatistic {
    private double avg;
    private double standardDeviation;

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public AvgStatistic buildAvgStatistic(List<Statistic> statisticList) {
        this.setAvg(findAvg(statisticList));
        this.setStandardDeviation(findStandardDeviation(statisticList));
        return this;
    }

    @Override
    public String toString() {
        return "Media: " + this.avg + "\nDeviazione standard: " + this.standardDeviation + "\n";
    }

    private double findAvg(List<Statistic> statisticList) {
        return statisticList.stream().mapToDouble(Statistic::getValue).average().getAsDouble();
    }

    private double findStandardDeviation(List<Statistic> statisticList) {
        double sum = 0;

        for (int k = 0; k < statisticList.size(); k++) {
            double value = statisticList.get(k).getValue();
            sum += Math.pow((value - getAvg()), 2);
        }
        return Math.sqrt(sum / statisticList.size());
    }
}
