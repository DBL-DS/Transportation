package player;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class PlayerThread extends Thread {
    private Player player;
    private boolean isNotOver;

    public PlayerThread(Player player) {
        this.player = player;
        this.isNotOver = true;
    }

    protected void setNotOver(boolean isNotOver) {
        this.isNotOver = isNotOver;
    }

    public void run() {
        while (isNotOver){
            try {
                Thread.sleep(Math.round(player.getPeriodBetweenDataUnit()/player.getSpeed()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.playNextFrame();
        }
    }
}
