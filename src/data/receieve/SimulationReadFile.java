package data.receieve;

import com.csvreader.CsvReader;
import data.structure.SimulatedVehicle;
import data.structure.VehicleCSV;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hugh on 2015/2/14 0014.
 */
public class SimulationReadFile {
    private ArrayList<SimulatedVehicle> simulationList;

    public SimulationReadFile() {
        simulationList = new ArrayList<SimulatedVehicle>();
    }

    public ArrayList<SimulatedVehicle> getListFromCsvFile(String filePath){
        CsvReader reader = readCsvFile(filePath);
        if(reader==null){
            return null;
        }
        try {
            reader.readHeaders();
            while (reader.readRecord()){
                SimulatedVehicle s = new SimulatedVehicle();
                s.setPositionX(Double.parseDouble(reader.get(VehicleCSV.PositionX)));
                s.setPositionY(Double.parseDouble(reader.get(VehicleCSV.PositionY)));
                s.setPositionZ(Double.parseDouble(reader.get(VehicleCSV.PositionZ)));
                s.setSpeed(Double.parseDouble(reader.get(VehicleCSV.Speed)));
                s.setAcceleration(Double.parseDouble(reader.get(VehicleCSV.Acceleration)));
                s.setSteeringWheel(Double.parseDouble(reader.get(VehicleCSV.SteeringWheel)));
                s.setGasPedal(Double.parseDouble(reader.get(VehicleCSV.GasPedal)));
                s.setBreakPedal(Double.parseDouble(reader.get(VehicleCSV.BreakPedal)));
                s.setClutchPedal(Double.parseDouble(reader.get(VehicleCSV.ClutchPedal)));
                s.setRpm(Double.parseDouble(reader.get(VehicleCSV.Rpm)));
                s.setLane(Double.parseDouble(reader.get(VehicleCSV.Lane)));
                s.setSimulationTime(Double.parseDouble(reader.get(VehicleCSV.SimulationTime)));
                s.setLaneLateralShift(Double.parseDouble(reader.get(VehicleCSV.LaneLateralShift)));
                s.setGear(Double.parseDouble(reader.get(VehicleCSV.Gear)));
                simulationList.add(s);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return simulationList;
    }

    private CsvReader readCsvFile(String filePath){
        CsvReader reader;
        try {
            reader = new CsvReader(filePath);
        } catch (FileNotFoundException e) {
            return null;
        }

        return reader;
    }

    public static void main(String[] args){
        String separator = System.getProperty("file.separator");
        String filePath = "."+separator+"csvFile"+separator+"use.csv";

        SimulationReadFile simulationReadFile = new SimulationReadFile();
        ArrayList<SimulatedVehicle> simulationList = simulationReadFile.getListFromCsvFile(filePath);
        System.out.println(simulationList.size());
        long start=System.currentTimeMillis();

    }
}
