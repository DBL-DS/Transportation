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

    public ReceiveDataFromNetwork(Player player,int port) {
        simulationUDP = new SimulationUDP(port);
        isNotOver = true;
        this.player = player;
    }

    public void setNotOver(boolean isNotOver) {
        this.isNotOver = isNotOver;
    }

    @Override
    public void run() {
        while (isNotOver){
            SimulatedVehicle vehicle = simulationUDP.getData();
            player.getNextData(vehicle);
        }
    }
}
