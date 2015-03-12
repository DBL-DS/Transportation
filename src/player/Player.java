package player;

import data.receieve.SimulationReadFile;
import data.structure.SimulatedVehicle;
import data.structure.VehicleHistory;

import java.util.ArrayList;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class Player {
    private double speed;
    private double dataUnitTimeGap;
    private ArrayList<VehicleHistory> historyList;
    private ArrayList<SimulatedVehicle> playingList;
    private SimulatedVehicle playingData;
    private int totalIndex;
    private int currentIndex;
    private PlayFile playFile;
    private Thread playerThread;
    private boolean dataUpdated;
    private boolean readyToPlay;
    private ReceiveDataFromNetwork network;
    private int port;
    private final long TestWaitTime = 2000;
    private int mode;
    public static final int FileMode = 1;
    public static final int SimulationMode = 2;

    public Player() {
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
        mode = this.FileMode;
    }
    public int getMode() {
        return mode;
    }
    public void setMode(int mode) {
        this.mode = mode;
    }
    private void addDataToHistoryList(){
        if (playingList!=null&&playingList.size()!=0){
            if (historyList==null){
                historyList = new ArrayList<VehicleHistory>();
            }
            historyList.add(new VehicleHistory(playingList));
            readyToPlay = true;
        }
    }
    public boolean isDataUpdated() {
        return dataUpdated;
    }
    public SimulatedVehicle getPlayingData() {
        dataUpdated = false;
        return playingData;
    }
    public ArrayList<VehicleHistory> getHistoryList() {
        return historyList;
    }

    //FileMode associated
    public void readDataFromFile(String filePath){
        SimulationReadFile reader = new SimulationReadFile();
        playingList = reader.getListFromCsvFile(filePath);
        if (playingList !=null){
            readyToPlay = true;
            totalIndex = playingList.size();
        }
        addDataToHistoryList();
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

    public boolean play(){
        if (mode!=this.FileMode){
            return false;
        }
        if (isReadyToPlay()){
            if (playFile == null){
                playFile = new PlayFile(this);
                playerThread = new Thread(playFile);
                playerThread.start();
            }else {
                playFile.setAskForWait(false);
                playFile.awake();
            }
            return true;
        } else {
            return false;
        }

    }
    public void pause(){
        if (playFile!=null&&mode==this.FileMode){
            playFile.setAskForWait(true);
        }
    }
    public void over(){
        if (playFile!=null){
            playFile.setNotOver(false);
            playFile = null;
        }
        currentIndex = 0;
    }
    protected boolean playNextFrame(){
        if (currentIndex<totalIndex){
            playingData = playingList.get(currentIndex);
            currentIndex++;
            dataUpdated = true;
            return true;
        }
        over();
        return false;
    }

    //historyList associated
    //TODO

    //SimulationMode associated
    public void setPort(int port) {
        this.port = port;
    }
    public int getPort() {
        return port;
    }

    public synchronized boolean testConnection(){
        TestConnection testConnection = new TestConnection(port);
        Thread testThread = new Thread(testConnection);
        testThread.start();

        if (!testThread.isAlive()){
            return true;
        }

        try {
            wait(TestWaitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (testThread.isAlive()){
            testConnection.close();
            return false;
        }else {
            return true;
        }
    }
    public boolean startNetworkThread(){
        network = new ReceiveDataFromNetwork(this,port);
        if (network!=null){
            playerThread = new Thread(network);
            playerThread.start();
            return true;
        }else {
            return false;
        }
    }
    public boolean endNetworkThread(){
        if (network!=null){
            network.setNotOver(false);
            if (playerThread!=null&&playerThread.isAlive()){
                network.close();
                network = null;
            }
            return true;
        }else {
            return false;
        }
    }
    public boolean startReceiveData(){
        if (playFile!=null){
            if (playFile.isPlaying()){
                return false;
            }
        }
        if (network!=null){
            playingList = new ArrayList<SimulatedVehicle>();
            network.setReceiving(true);
            network.continuePlay();
            return true;
        }else {
            return false;
        }
    }
    public boolean endReceiveData(){
        if (network!=null){
            addDataToHistoryList();
            network.setReceiving(false);

            addDataToHistoryList();
            return true;
        }else {
            return false;
        }
    }
    protected void getNextData(SimulatedVehicle vehicle){
        playingData = vehicle;
        totalIndex += 1;
        currentIndex += 1;
        playingList.add(playingData);
        dataUpdated = true;

    }
}
