package player;

import data.receieve.SimulationRead;
import data.structure.SimulatedVehicle;

import java.util.ArrayList;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class Player {
    private double speed;
    private double periodBetweenDataUnit;
    private ArrayList<SimulatedVehicle> simulatedVehicles;
    private SimulatedVehicle playingData;
    private PlayerThread playerThread;
    private boolean dataIsUpdated;

    //导入文件数据初始化方法
    public Player(String filePath) {
        initDefaultData();
        SimulationRead reader = new SimulationRead();
        simulatedVehicles = reader.getListFromCsvFile(filePath);
    }

    //接收模拟舱数据初始化方法
    public Player(){
        initDefaultData();
    }

    public void initDefaultData(){
        speed = 1;
        dataIsUpdated = false;
        periodBetweenDataUnit = 1000;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }
    protected double getSpeed(){
        return this.speed;
    }

    protected double getPeriodBetweenDataUnit() {
        return periodBetweenDataUnit;
    }

    public SimulatedVehicle getPlayingData() {
        dataIsUpdated = false;
        return playingData;
    }

    public void play(){
        if (playerThread==null){
            playerThread = new PlayerThread(this);
            playerThread.start();
        }

    }
    public void pause(){
        try {
            playerThread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected boolean playNextFrame(){
        if (simulatedVehicles.size()!=0){
            playingData = simulatedVehicles.remove(0);
            dataIsUpdated = true;
            return true;
        }

        return false;
    }
}
