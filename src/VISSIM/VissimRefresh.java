package VISSIM;

import player.Player;

/**
 * Created by Allen on 2015/3/12.
 */
public class VissimRefresh implements Runnable {
    private VissimControl control;
    private boolean isNotOver;

    public VissimRefresh(VissimControl control) {
        this.control = control;
        initData();
    }
    private void initData(){
        isNotOver = true;
    }

    @Override
    public void run() {
        while (isNotOver){
            control.refresh();
        }
    }
}
