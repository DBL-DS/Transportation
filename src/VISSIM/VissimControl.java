package VISSIM;

import data.vehicle.structure.SimulatedVehicle;
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
    private VissimRefresh vissimRefresh;
    private Thread refreshThread;

    public VissimControl() {
        initData();
    }

    public void startRefreshThread() {
       vissimRefresh = new VissimRefresh(this);
        refreshThread = new Thread(vissimRefresh);
        refreshThread.start();
    }

    public int getCount() {
        return count;
    }

    private void initData(){
        vissim = new VISSIM();
        vissim.loadpath("I:\\迅雷下载\\VISSIM510\\3-4.inpx");
        simulation = vissim.getSimlation();
        count=0;
        net = vissim.getnet();

    }
    public void receiveData(SimulatedVehicle vehicle){
        this.simulatedVehicle = vehicle;
    }
    public void refresh(){
        Vehicle vehicle = net.getVehicle(1);
        if (simulatedVehicle!=null){
            vehicle.setSpeed(simulatedVehicle.getSpeed());
            System.out.println(simulatedVehicle.getSpeed());
        }
        simulation.RunSingle();
        count++;
    }
    public void initVissim()
    {
        while (count<114)
        {
            simulation.RunSingle();
            count++;
        }
        while (true){
            refresh();
        }
    }
    public void close(){
        vissim.exit();
    }
}
