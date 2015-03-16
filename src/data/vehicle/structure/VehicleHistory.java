package data.vehicle.structure;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hugh on 2015/3/12 0012.
 */
public class VehicleHistory {
    private ArrayList<SimulatedVehicle> vehicleList;
    private String saveTime;
    private int hashCode;

    public VehicleHistory(ArrayList<SimulatedVehicle> vehicleList) {
        this.vehicleList = vehicleList;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        saveTime = df.format(new Date());

        hashCode = vehicleList.hashCode();
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
}
