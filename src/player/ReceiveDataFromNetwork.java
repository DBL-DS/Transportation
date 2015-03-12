package player;

import data.receieve.SimulationUDP;
import data.structure.SimulatedVehicle;

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

    @Override
    public void run() {
        while (isNotOver){
            if (isReceiving){
                SimulatedVehicle vehicle = simulationUDP.getData();
                player.getNextData(vehicle);
            }
        }
    }
}
