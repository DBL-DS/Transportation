package player;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class PlayNext implements Runnable {
    private Player player;
    private boolean isNotOver;

    public PlayNext(Player player) {
        this.player = player;
        this.isNotOver = true;
    }

    protected void setNotOver(boolean isNotOver) {
        this.isNotOver = isNotOver;
    }
    protected void pause(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void play(){
        notifyAll();
    }

    @Override
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
