package ui;

import data.vehicle.structure.SimulatedVehicle;
import player.Player;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class ListenPlayingDataThread extends Thread{
    private MainForm mainForm;
    private boolean isNotOver;
    public ListenPlayingDataThread(MainForm mainForm) {
        this.mainForm = mainForm;
        isNotOver = true;
    }

    public void run(){
        while (isNotOver){
            Player p = mainForm.getPlayer();
            if (p==null){
                continue;
            }
            if (p.isDataUpdated()){
                SimulatedVehicle newVehicle = p.getPlayingData();
                mainForm.setUI(newVehicle);
            } else if (p.isPlayOver()){
                mainForm.playIsOver();
            }
        }
    }
}
