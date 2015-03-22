package data.valuation.structure;

import java.util.ArrayList;

/**
 * Created by Hugh on 2015/3/16 0016.
 */
public class Valuation {
    private double totalTime;
    private double totalLaneChange;
    private double averageSpeed;
    private ArrayList<Double> queueLengthList;
    private double score;
    private double totalCarbon;
    private double sharpBrake;

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getTotalLaneChange() {
        return totalLaneChange;
    }

    public void setTotalLaneChange(double totalLaneChange) {
        this.totalLaneChange = totalLaneChange;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public ArrayList<Double> getQueueLengthList() {
        return queueLengthList;
    }

    public void setQueueLengthList(ArrayList<Double> queueLengthList) {
        this.queueLengthList = queueLengthList;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getTotalCarbon() {
        return totalCarbon;
    }

    public void setTotalCarbon(double totalCarbon) {
        this.totalCarbon = totalCarbon;
    }

    public double getSharpBrake() {
        return sharpBrake;
    }

    public void setSharpBrake(double sharpBrake) {
        this.sharpBrake = sharpBrake;
    }
}
