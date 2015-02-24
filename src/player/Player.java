package player;

import data.receieve.SimulationRead;
import data.structure.SimulatedVehicle;
import ui.MainForm;

import java.util.ArrayList;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class Player {
    private double speed;
    private double dataUnitTimeGap;
    private ArrayList<SimulatedVehicle> simulatedVehicles;
    private SimulatedVehicle playingData;
    private int totalIndex;
    private int currentIndex;
    private PlayNext playNext;
    private Thread playerThread;
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
        dataUnitTimeGap = 1000;
        readyToPlay = false;
        currentIndex = 0;
    }
    private void readDataFromFile(String filePath){
        SimulationRead reader = new SimulationRead();
        simulatedVehicles = reader.getListFromCsvFile(filePath);
        if (simulatedVehicles!=null){
            readyToPlay = true;
            totalIndex = simulatedVehicles.size();
        }
    }

    public boolean isDataUpdated() {
        return dataUpdated;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }
    public double getSpeed(){
        return this.speed;
    }
    public void setDataUnitTimeGap(double dataUnitTimeGap) {
        this.dataUnitTimeGap = dataUnitTimeGap;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public double getDataUnitTimeGap() {
        return dataUnitTimeGap;
    }

    public SimulatedVehicle getPlayingData() {
        dataUpdated = false;
        return playingData;
    }
    public double getCurrentPlayingSpeed(){
        double currentSpeed = dataUnitTimeGap /speed;
        return currentSpeed;
    }
    public double getTotalTime(){
        return totalIndex*dataUnitTimeGap;
    }
    public double getCurrentTime(){
        return currentIndex*dataUnitTimeGap;
    }

    public int getTotalIndex() {
        return totalIndex;
    }

    public boolean isReadyToPlay() {
        return readyToPlay;
    }
    public boolean isPlayOver(){
        if (currentIndex == totalIndex){
            return true;
        }else {
            return false;
        }
    }

    public void play(){
        if (playNext ==null){
            playNext = new PlayNext(this);
            playerThread = new Thread(playNext);
            playerThread.start();
        }else {
            playNext.setAskForWait(false);
            playNext.awake();
        }
    }
    public void pause(){
        playNext.setAskForWait(true);
    }
    public void over(){
        playNext.setNotOver(false);
        playNext = null;
        currentIndex = 0;
    }
    protected boolean playNextFrame(){
        if (currentIndex<totalIndex){
            playingData = simulatedVehicles.get(currentIndex);
            currentIndex++;
            dataUpdated = true;
            return true;
        }
        over();
        return false;
    }
}
