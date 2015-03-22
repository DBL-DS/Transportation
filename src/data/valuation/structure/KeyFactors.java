package data.valuation.structure;

import java.util.ArrayList;

/**
 * Created by Allen on 2015/3/22.
 */
public class KeyFactors {
    private ArrayList<Double> zRotationalSpeed;
    private ArrayList<Double> zDriftAngel;
    private ArrayList<Double> zOffset;

    public ArrayList<Double> getzRotationalSpeed() {
        return zRotationalSpeed;
    }

    public void setzRotationalSpeed(ArrayList<Double> zRotationalSpeed) {
        this.zRotationalSpeed = zRotationalSpeed;
    }

    public ArrayList<Double> getzDriftAngel() {
        return zDriftAngel;
    }

    public void setzDriftAngel(ArrayList<Double> zDriftAngel) {
        this.zDriftAngel = zDriftAngel;
    }

    public ArrayList<Double> getzOffset() {
        return zOffset;
    }

    public void setzOffset(ArrayList<Double> zOffset) {
        this.zOffset = zOffset;
    }
}
