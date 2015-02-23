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
    private boolean dataUpdated;
    private boolean readyToPlay;

    //导入文件数据初始化方法
    public Player(String filePath) {
        initDefaultData();
        readDataFromFile(filePath);
    }

    //接收模拟舱数据初始化方法
    public Player(){
        initDefaultData();
    }


    public void initDefaultData(){
        speed = 1;
        dataUpdated = false;
        periodBetweenDataUnit = 1000;
        readyToPlay = false;
    }
    private void readDataFromFile(String filePath){
        SimulationRead reader = new SimulationRead();
        simulatedVehicles = reader.getListFromCsvFile(filePath);
        if (simulatedVehicles!=null){
            readyToPlay = true;
        }
    }

    public boolean isDataUpdated() {
        return dataUpdated;
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
        dataUpdated = false;
        return playingData;
    }

    public boolean isReadyToPlay() {
        return readyToPlay;
    }

    public void play(){
        if (playerThread==null){
            playerThread = new PlayerThread(this);
            playerThread.start();
        }else {
            playerThread.notifyAll();
        }

    }
    public void pause(){
        try {
            playerThread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void over(){
        playerThread.setNotOver(false);
    }
    protected boolean playNextFrame(){
        if (simulatedVehicles.size()!=0){
            playingData = simulatedVehicles.remove(0);
            dataUpdated = true;
            return true;
        }

        return false;
    }
}
