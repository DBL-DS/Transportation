package VISSIM;

import data.structure.SimulatedVehicle;

/**
 * Created by Allen on 2015/3/12.
 */
public class VissimControl {
    private VISSIM vissim;
    private Simulation simulation;
    private int count;
    Net net;

    public VissimControl() {
        initData();
    }
    private void initData(){
        vissim = new VISSIM();
        vissim.loadpath("I:\\迅雷下载\\VISSIM510\\3-4.inpx");
        simulation = vissim.getSimlation();
        count=0;
        net = vissim.getnet();
    }
    public void receiveData(SimulatedVehicle simulatedVehicle){
        simulation.RunSingle();
        if(count > 114)
        {
            Vehicle vehicle = net.getVehicle(1);
            if (simulatedVehicle!=null){
                vehicle.setSpeed(simulatedVehicle.getSpeed());
            }
        }
        count++;

    }
}
