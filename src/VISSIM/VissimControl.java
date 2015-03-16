package VISSIM;

import data.structure.SimulatedVehicle;
import player.Player;

/**
 * Created by Allen on 2015/3/12.
 */
public class VissimControl {
    private VISSIM vissim;
    private Simulation simulation;
    private int count;
    private Net net;
    private SimulatedVehicle simulatedVehicle;
    private Player player;

    public VissimControl() {
        initData();
    }

    public int getCount() {
        return count;
    }

    private void initData(){
        vissim = new VISSIM();
        vissim.loadpath("D:\\Software_Document\\Java\\3-4.inpx");
        simulation = vissim.getSimlation();
        count=0;
        net = vissim.getnet();
    }

    public void receiveData(SimulatedVehicle vehicle){
        this.simulatedVehicle = vehicle;
    }
    public void refresh(){
        simulation.RunSingle();
//        if(count >= 114)
//        {
            Vehicle vehicle = net.getVehicle(1);
//            if (simulatedVehicle!=null){
//                vehicle.setSpeed(simulatedVehicle.getSpeed());
                vehicle.setSpeed(10);
//            }
//        }
//        count++;
//        if (simulatedVehicle!=null)
//            System.out.println(String.format("%.3f",simulatedVehicle.getSimulationTime()));
    }
    public void initVissim()
    {
        while (count<114)
        {
            simulation.RunSingle();
            count++;
        }
    }
}
