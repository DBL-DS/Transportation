package data.vehicle.receieve;

import com.csvreader.CsvReader;
import data.vehicle.structure.SimulatedVehicle;
import data.vehicle.structure.VehicleCSV;

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
                s.setSimulationTime(Double.parseDouble(reader.get(VehicleCSV.SimulationTime)));
                s.setPositionX(Double.parseDouble(reader.get(VehicleCSV.PositionX)));
                s.setPositionY(Double.parseDouble(reader.get(VehicleCSV.PositionY)));
                s.setPositionZ(Double.parseDouble(reader.get(VehicleCSV.PositionZ)));
                s.setSpeed(Double.parseDouble(reader.get(VehicleCSV.Speed)));
                s.setAcceleration(Double.parseDouble(reader.get(VehicleCSV.Acceleration)));
                s.setDriftAngle(Double.parseDouble(reader.get(VehicleCSV.DriftAngle)));
                s.setOffset(Double.parseDouble(reader.get(VehicleCSV.Offset)));
                s.setSteeringWheel(Double.parseDouble(reader.get(VehicleCSV.SteeringWheel)));
                s.setRotationalSpeed(Double.parseDouble(reader.get(VehicleCSV.RotationalSpeed)));
                s.setGasPedal(Double.parseDouble(reader.get(VehicleCSV.GasPedal)));
                s.setBreakPedal(Double.parseDouble(reader.get(VehicleCSV.BreakPedal)));
                s.setClutchPedal(Double.parseDouble(reader.get(VehicleCSV.ClutchPedal)));
                s.setGear(Double.parseDouble(reader.get(VehicleCSV.Gear)));
                s.setRpm(Double.parseDouble(reader.get(VehicleCSV.Rpm)));
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
        String filePath = "."+separator+"csvFile"+separator+"standard.csv";

        SimulationReadFile simulationReadFile = new SimulationReadFile();
        ArrayList<SimulatedVehicle> simulationList = simulationReadFile.getListFromCsvFile(filePath);
        System.out.println(simulationList.size());
        long start=System.currentTimeMillis();

    }
}
