package player;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class PlayFile implements Runnable {
    private Player player;
    private boolean isNotOver;
    private boolean askForWait;

    public PlayFile(Player player) {
        this.player = player;
        this.isNotOver = true;
        this.askForWait = false;
    }

    protected void setNotOver(boolean isNotOver) {
        this.isNotOver = isNotOver;
    }

    protected void setAskForWait(boolean askForWait) {
        this.askForWait = askForWait;
    }

    protected synchronized void pause(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected synchronized void awake(){
        notifyAll();
    }

    public void run() {
        while (isNotOver){
            try {
                Thread.sleep(Math.round(player.getDataUnitTimeGap()/player.getSpeed()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.playNextFrame();
            while (askForWait){
                pause();
            }
        }
    }
}
