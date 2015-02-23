package player;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class PlayerThread extends Thread {
    private Player player;

    public PlayerThread(Player player) {
        this.player = player;
    }

    public void run() {
        while (true){
            try {
                Thread.sleep(Math.round(player.getPeriodBetweenDataUnit()/player.getSpeed()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.playNextFrame();
        }
    }
}
