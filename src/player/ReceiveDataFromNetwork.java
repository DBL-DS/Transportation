package player;

import data.vehicle.receieve.SimulationUDP;
import data.vehicle.structure.SimulatedVehicle;

/**
 * Created by Hugh on 2015/3/1 0001.
 */
public class ReceiveDataFromNetwork implements Runnable {
    private SimulationUDP simulationUDP;
    private boolean isNotOver;
    private Player player;
    private boolean isReceiving;

    public ReceiveDataFromNetwork(Player player,int port) {
        simulationUDP = new SimulationUDP(port);
        isNotOver = true;
        isReceiving = false;
        this.player = player;
    }
    public void close(){
        simulationUDP.close();
    }

    public void setNotOver(boolean isNotOver) {
        this.isNotOver = isNotOver;
    }

    public void setReceiving(boolean isReceiving) {
        this.isReceiving = isReceiving;
    }
    public synchronized void pause(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void continuePlay(){
        notifyAll();
    }

    @Override
    public void run() {
        while (isNotOver){
            if (isReceiving){
                if (player!=null){
                    SimulatedVehicle vehicle = simulationUDP.getData();
                    player.getNextData(vehicle);
                }
            }else {
                pause();
            }
        }
    }
}
