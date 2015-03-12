package player;

import data.receieve.SimulationUDP;

/**
 * Created by Hugh on 2015/3/10 0010.
 */
public class TestConnection implements Runnable {
    SimulationUDP udp;
    private int port;

    public TestConnection(int port) {
        this.port = port;
        udp = new SimulationUDP(port);
    }
    public void close(){
        udp.close();
    }
    @Override
    public void run() {
        udp.getData();
        udp.close();
    }
}
