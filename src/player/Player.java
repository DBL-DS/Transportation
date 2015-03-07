package player;

import data.receieve.SimulationReadFile;
import data.structure.SimulatedVehicle;

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
    private PlayFile playFile;
    private Thread playerThread;
    private boolean dataUpdated;
    private boolean readyToPlay;
    private ReceiveDataFromNetwork network;
    private int port;

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
        totalIndex = 0;
        currentIndex = 0;
        port = 6000;
    }
    private void readDataFromFile(String filePath){
        SimulationReadFile reader = new SimulationReadFile();
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

    public void setPort(int port) {
        this.port = port;
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
        if (playFile ==null){
            playFile = new PlayFile(this);
            playerThread = new Thread(playFile);
            playerThread.start();
        }else {
            playFile.setAskForWait(false);
            playFile.awake();
        }
    }
    public void pause(){
        playFile.setAskForWait(true);
    }
    public void over(){
        if (playFile!=null){
            playFile.setNotOver(false);
            playFile = null;
        }
        if (network!=null){
            network.setNotOver(false);
            network = null;
        }
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

    public void startRefreshDataFromNetwork(){
        network = new ReceiveDataFromNetwork(this,port);
        playerThread = new Thread(network);
        playerThread.start();
    }
    protected void getNextData(SimulatedVehicle vehicle){
        playingData = vehicle;
        totalIndex += 1;
        currentIndex += 1;
        dataUpdated = true;
    }
}
