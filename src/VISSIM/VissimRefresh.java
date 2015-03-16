package VISSIM;

/**
 * Created by Allen on 2015/3/12.
 */
public class VissimRefresh implements Runnable {
    private VissimControl control;
    private boolean isNotOver;
    private boolean newControl;
    private boolean exitControl;
    private boolean initControl;
    private boolean startRefresh;

    public VissimRefresh(VissimControl control) {
        this.control = control;
        initData();
    }
    private void initData(){
        isNotOver = true;
        newControl = false;
        initControl = false;
        startRefresh = false;
    }
    public synchronized void pause(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void next(){
        notifyAll();
    }
    public void exit(){
        isNotOver = false;
        newControl = exitControl = initControl = startRefresh = false;
        next();
    }
    public void startControl(){
        newControl = true;
        exitControl = initControl = startRefresh = false;
        next();
    }
    public void exitControl(){
        exitControl = true;
        newControl = initControl = startRefresh = false;
        next();
    }
    public void initControl(){
        if (newControl){
            initControl = true;
            newControl = exitControl = startRefresh = false;
            next();
        }
    }
    public void startRefresh(){
        if (initControl){
            startRefresh = true;
            newControl = exitControl = startRefresh = false;
            next();
        }
    }
    public void pauseRefresh(){
        startRefresh = false;
    }
    @Override
    public void run() {
        while (isNotOver){
            pause();
            if (newControl){
                if (control==null)
                    control = new VissimControl();
            }
            if (exitControl){
                if (control!=null){
                    control = null;
                }
            }
            if (initControl){
                if (control!=null)
                    control.initVissim();
            }
            while (startRefresh){
                if (control==null){
                    break;
                }
                control.refresh();
            }
        }
    }
}
