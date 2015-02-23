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
public class SimulationRead {
    private ArrayList<SimulatedVehicle> simulationList;

    public SimulationRead() {
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
                s.setSaveTime(Double.parseDouble(reader.get(VehicleCSV.SaveTime)));
                s.setSimulationTime(Double.parseDouble(reader.get(VehicleCSV.SimulationTime)));
                s.setHandleDegree(Double.parseDouble(reader.get(VehicleCSV.HandleDegree)));
                s.setGasPedal(Double.parseDouble(reader.get(VehicleCSV.GasPedal)));
                s.setBreakPedal(Double.parseDouble(reader.get(VehicleCSV.BreakPedal)));
                s.setClutchPedal(Double.parseDouble(reader.get(VehicleCSV.ClutchPedal)));
                s.setSpeed(Double.parseDouble(reader.get(VehicleCSV.Speed)));
                s.setRpm(Double.parseDouble(reader.get(VehicleCSV.Rpm)));
                s.setAccelerationX(Double.parseDouble(reader.get(VehicleCSV.AccelerationX)));
                s.setAccelerationY(Double.parseDouble(reader.get(VehicleCSV.AccelerationY)));
                s.setAccelerationZ(Double.parseDouble(reader.get(VehicleCSV.AccelerationZ)));
                s.setPositionX(Double.parseDouble(reader.get(VehicleCSV.PositionX)));
                s.setPositionY(Double.parseDouble(reader.get(VehicleCSV.PositionY)));
                s.setPositionZ(Double.parseDouble(reader.get(VehicleCSV.PositionZ)));
                s.setLane(Double.parseDouble(reader.get(VehicleCSV.Lane)));
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

        SimulationRead simulationRead = new SimulationRead();
        ArrayList<SimulatedVehicle> simulationList = simulationRead.getListFromCsvFile(filePath);
        System.out.println(simulationList.size());
        long start=System.currentTimeMillis();

    }
}
