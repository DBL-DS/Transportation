package data.history.structure;

import data.valuation.receive.Zscore;
import data.valuation.structure.Valuation;
import data.vehicle.structure.SimulatedVehicle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hugh on 2015/3/16 0016.
 */
public class DataHistory {
    private int hashCode;
    private ArrayList<SimulatedVehicle> vehicleList;
    private String saveTime;
    private Valuation valuation;
    private Zscore scores;

    public DataHistory(ArrayList<SimulatedVehicle> vehicleList) {
        if (vehicleList!=null){
            this.vehicleList = vehicleList;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            saveTime = df.format(new Date());
            hashCode = vehicleList.hashCode();
        } else {
            System.err.println("DataHistory 初始化错误，传入的vehicleList为空！");
        }
    }

    public ArrayList<SimulatedVehicle> getVehicleList() {
        return vehicleList;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public int getHashCode() {
        return hashCode;
    }
    //TODO

    public Zscore getScores() {
        return scores;
    }

    public void setScores(Zscore scores) {
        this.scores = scores;
    }
}
