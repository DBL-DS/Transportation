package ui;

import VISSIM.*;
import data.structure.SimulatedVehicle;
import player.Player;
import player.PlayerSpeed;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Hugh on 2015/2/14 0014.
 */
public class MainForm {
    private JFrame mainFrame;
    private JPanel wrapPanel;
    private JPanel controlPanel;
    private JLabel timeLabel;
    private JSlider timeSlider;
    private JPanel viewChoicePanel;
    private JButton trailButton;
    private JButton velocityButton;
    private JButton laneButton;
    private JButton accelerationButton;
    private JButton evaluationButton;
    private JPanel timeControlPanel;
    private JPanel viewPanel;
    private JButton dashboardButton;
    private JPanel simulationControlPanel;
    private JButton startNetworkButton;
    private JButton simulationSettingsButton;
    private JButton importDataButton;
    private JTextField filePathTextField;
    private JButton filePathChooseButton;
    private JButton playAndPauseButton;
    private JButton playSpeed1;
    private JButton playSpeed2;
    private JButton playSpeed3;
    private JButton playSpeed4;
    private JButton playSpeed5;
    private JButton playSpeed6;
    private JButton playSpeed7;
    private JButton playSpeed8;
    private JLabel filePathLabel;
    private JButton baseDataButton;
    private JTextField dataUnitTimeGapTextField;
    private JButton setDataUnitTimeGapButton;
    private JLabel dataUnitTimeGapLabel;
    private JLabel speedLabel;
    private JLabel currentDataPlaySpeedLabel;
    private JLabel totalTimeLabel;
    private JLabel currentTimeLabel;
    private JPanel statusPanel;
    private JButton endNetworkButton;
    private JButton startReceiveButton;
    private JButton createNewFrameButton;
    private JPanel viewControlPanel;
    private JTabbedPane tabbedPane1;
    private JButton startSimulationProjectButton;
    private JButton endSimulationProjectButton;
    private JPanel fileControlPanel;
    private JButton testConnectionButton;
    private JButton endReceiveButton;
    private JButton initVISSIMButton;
    private JButton inputButton;
    private JButton startVISSIMButton;
    private BaseData base;
    private Trail trail;
    private Lane lane;
    private Velocity velocity;
    private Acceleration acceleration;
    private Dashboard dashboard;
    private Evaluation evaluation;
    private JPanel now;
    private Welcome welcome;
    private Player player;
    private ListenPlayingDataThread listenPlayingDataThread;
    private VissimControl vissimControl;
    private boolean vissimConnect;

    public MainForm() {
        initViewPanels();
        setTransformViewPanelButtonEvent();
        setViewControlPanelEvent();
        setFileReadingControlPanelEvent();
        setSimulationControlPanelEvent();
        setVISSIMControlPanelEvent();
        startListenPlayingDataThread();
        initPlayer();


    }

    public JPanel getWrapPanel() {
        return wrapPanel;
    }
    protected Player getPlayer(){
        return player;
    }

    private void initViewPanels(){
        base = new BaseData();
        trail = new Trail();
        lane = new Lane();
        velocity = new Velocity();
        acceleration = new Acceleration();
        dashboard = new Dashboard();
        evaluation = new Evaluation();
        welcome = new Welcome();
        transformViewPanel(welcome.getWelcomePanel());
        vissimConnect = false;
    }
    private void setTransformViewPanelButtonEvent(){
        baseDataButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(base.getBaseDataPanel());
            }
        });
        trailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(trail.getTrailPanel());
            }
        });
        laneButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(lane.getLanePanel());
            }
        });
        velocityButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(velocity.getVelocityPanel());
            }
        });
        accelerationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(acceleration.getAccelerationPanel());
            }
        });
        dashboardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(dashboard.getDashboardPanel());
            }
        });
        evaluationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(evaluation.getEvaluationPanel());
            }
        });

    }
    private void setViewControlPanelEvent(){
        createNewFrameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e1) {
                super.mouseClicked(e1);
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                JFrame frame = new JFrame();
                frame.setContentPane(now);
                frame.pack();
                frame.setVisible(true);
                //居中显示窗体
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension frameSize = frame.getSize();
                frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
            }
        });
    }
    private void setFileReadingControlPanelEvent(){
        filePathChooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnVal = fileChooser.showOpenDialog(fileChooser);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    filePathTextField.setText(filePath);
                }
            }
        });
        importDataButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                resetPlayAndPauseButton();
                resetTimeSlider();
                String filePath = filePathTextField.getText();
                player.readDataFromFile(filePath);
                if (player.isReadyToPlay()) {
                    AlertDialog dialog = new AlertDialog("读取数据成功！");
                    dialog.showDialog();
                    dialog = null;
                    refreshStatusPanel();
                } else {
                    AlertDialog dialog = new AlertDialog("读取数据失败！请检查文件路径和文件！");
                    dialog.showDialog();
                    dialog = null;
                }
            }
        });
        playAndPauseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (player!=null){
                    if (playAndPauseButton.getText().equals("播放")){
                        if (player.isReadyToPlay()){
                            if (now == welcome.getWelcomePanel()){
                                transformViewPanel(base.getBaseDataPanel());
                            }
                            if (player.play()){
                                playAndPauseButton.setText("暂停");
                            }else {
                                new AlertDialog("播放失败，请确认已经结束了模拟舱任务").showDialog();
                            }
                        }
                    }else {
                        player.pause();
                        playAndPauseButton.setText("播放");
                    }
                }else {
                    alertToImportDataFirst();
                }
            }
        });
        setDataUnitTimeGapButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (player!=null){
                    String gapTimeString = dataUnitTimeGapTextField.getText();
                    try{
                        double gapTime = Double.parseDouble(gapTimeString);
                        player.setDataUnitTimeGap(gapTime);
                        refreshStatusPanel();
                    }catch (NumberFormatException e1){
                        AlertDialog alert = new AlertDialog("输入格式错误！");
                        alert.showDialog();
                    }
                }else {
                    alertToImportDataFirst();
                }
            }
        });

        ActionListener speedSetting = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player!=null) {
                    if (e.getSource() == playSpeed1) {
                        player.setSpeed(PlayerSpeed.playSpeed1);
                    } else if (e.getSource() == playSpeed2){
                        player.setSpeed(PlayerSpeed.playSpeed2);
                    } else if (e.getSource() == playSpeed3){
                        player.setSpeed(PlayerSpeed.playSpeed3);
                    } else if (e.getSource() == playSpeed4){
                        player.setSpeed(PlayerSpeed.playSpeed4);
                    } else if (e.getSource() == playSpeed5){
                        player.setSpeed(PlayerSpeed.playSpeed5);
                    } else if (e.getSource() == playSpeed6){
                        player.setSpeed(PlayerSpeed.playSpeed6);
                    } else if (e.getSource() == playSpeed7){
                        player.setSpeed(PlayerSpeed.playSpeed7);
                    } else if (e.getSource() == playSpeed8){
                        player.setSpeed(PlayerSpeed.playSpeed8);
                    }

                    refreshStatusPanel();
                }else {
                    alertToImportDataFirst();
                }
            }
        };
        playSpeed1.addActionListener(speedSetting);
        playSpeed2.addActionListener(speedSetting);
        playSpeed3.addActionListener(speedSetting);
        playSpeed4.addActionListener(speedSetting);
        playSpeed5.addActionListener(speedSetting);
        playSpeed6.addActionListener(speedSetting);
        playSpeed7.addActionListener(speedSetting);
        playSpeed8.addActionListener(speedSetting);

        timeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (player!=null){
                    int value = timeSlider.getValue();
                    int max = timeSlider.getMaximum();
                    int currentIndex = Integer.valueOf(""+Math.round((double)value/max*player.getTotalIndex()));
                    player.setCurrentIndex(currentIndex);
                    refreshStatusPanel();
                }
            }
        });

    }

    private void setSimulationControlPanelEvent(){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==startSimulationProjectButton){
                    player.setMode(Player.SimulationMode);
                    enableAndDisableButton(startSimulationProjectButton);
                }else if (e.getSource()==endSimulationProjectButton){
                    player.endNetworkThread();
                    player.setMode(Player.FileMode);
                    enableAndDisableButton(endSimulationProjectButton);
                }else if (e.getSource()==simulationSettingsButton){
                    SimulationSetting setting = new SimulationSetting(player);
                    setting.showDialog();
                }else if (e.getSource()==testConnectionButton){
                    AlertDialog alertDialog;
                    if (player.testConnection()){
                        alertDialog = new AlertDialog("数据接入成功！");
                    }else {
                        alertDialog = new AlertDialog("未检测到数据接入！");
                    }
                    alertDialog.showDialog();
                }else if (e.getSource()==endSimulationProjectButton){
                    enableAndDisableButton(endSimulationProjectButton);
                }else if (e.getSource()==startNetworkButton){
                    if (player.startNetworkThread()){
                        enableAndDisableButton(startNetworkButton);
                    }
                }else if (e.getSource()==endNetworkButton){
                    player.endNetworkThread();
                    enableAndDisableButton(endNetworkButton);
                }else if (e.getSource()==startReceiveButton){
                    if (player.startReceiveData()){
                        enableAndDisableButton(startReceiveButton);
                    } else {
                        new AlertDialog("尝试接收失败，可能原因：\n\t1.正在播放文件\n\t2.网络异常").showDialog();
                    }
                }else if (e.getSource()==endReceiveButton){
                    player.endReceiveData();
                    enableAndDisableButton(endReceiveButton);
                }
            }
        };
        startSimulationProjectButton.addActionListener(listener);
        endSimulationProjectButton.addActionListener(listener);
        simulationSettingsButton.addActionListener(listener);
        testConnectionButton.addActionListener(listener);
        startNetworkButton.addActionListener(listener);
        endNetworkButton.addActionListener(listener);
        startReceiveButton.addActionListener(listener);
        endReceiveButton.addActionListener(listener);
    }



    private void setVISSIMControlPanelEvent()
    {
        startVISSIMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vissimControl == null){
                    vissimControl = new VissimControl();
                }
            }
        });
        initVISSIMButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                vissimControl.passStart();
            }
        });
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vissimConnect = true;
                vissimControl.startRefreshThread();
            }
        });
    }
    private void startListenPlayingDataThread(){
        listenPlayingDataThread = new ListenPlayingDataThread(this);
        listenPlayingDataThread.start();
    }
    private void initPlayer(){
        player = new Player();
    }

    private void enableAndDisableButton(JButton thisButton){
        if (thisButton==startSimulationProjectButton){
            thisButton.setEnabled(false);
            endSimulationProjectButton.setEnabled(true);
            simulationSettingsButton.setEnabled(true);
            testConnectionButton.setEnabled(true);
            startNetworkButton.setEnabled(true);
        }else if(thisButton==endSimulationProjectButton){
            thisButton.setEnabled(false);
            startSimulationProjectButton.setEnabled(true);
            simulationSettingsButton.setEnabled(false);
            testConnectionButton.setEnabled(false);
            startNetworkButton.setEnabled(false);
            endNetworkButton.setEnabled(false);
            startReceiveButton.setEnabled(false);
            endReceiveButton.setEnabled(false);
        }else if (thisButton==startNetworkButton){
            thisButton.setEnabled(false);
            endNetworkButton.setEnabled(true);
            startReceiveButton.setEnabled(true);
        }else if (thisButton==endNetworkButton){
            thisButton.setEnabled(false);
            startNetworkButton.setEnabled(true);
            startReceiveButton.setEnabled(false);
            endReceiveButton.setEnabled(false);
        }else if (thisButton==startReceiveButton){
            thisButton.setEnabled(false);
            endReceiveButton.setEnabled(true);
        }else if (thisButton==endReceiveButton){
            thisButton.setEnabled(false);
            startReceiveButton.setEnabled(true);
        }
    }
    private void transformViewPanel(JPanel panel){
        if (now==null || !now.equals(panel)){
            now = panel;
            viewPanel.removeAll();
            viewPanel.setLayout(new GridLayout());
            viewPanel.add(now);
            viewPanel.validate();
            viewPanel.repaint();
        }
    }
    protected void setUI(SimulatedVehicle simulatedVehicle){
        refreshStatusPanel();
        refreshTimeSlider();
        base.receiveData(simulatedVehicle);
        dashboard.receiveData(simulatedVehicle);
        velocity.receiveData(simulatedVehicle);
        acceleration.receiveData(simulatedVehicle);
        trail.receiveData(simulatedVehicle);
        lane.receiveData(simulatedVehicle);
        if (vissimConnect){
            vissimControl.receiveData(simulatedVehicle);
        }
    }
    public void refreshStatusPanel(){
        if (player!=null){
            refreshDataUnitTimeGap();
            refreshSpeed();
            refreshCurrentPlayingSpeed();
            refreshTotalTime();
            refreshCurrentTime();
        }else {
            alertToImportDataFirst();
        }
    }
    public void refreshDataUnitTimeGap(){
        if (player!=null){
            double gap = player.getDataUnitTimeGap();
            if (gap<1000){
                dataUnitTimeGapLabel.setText(gap+"ms");
            }else {
                dataUnitTimeGapLabel.setText(gap/1000+"s");
            }
        }
    }
    public void refreshSpeed(){
        if (player!=null){
            double speed = player.getSpeed();
            speedLabel.setText(speed+"");
        }
    }
    public void refreshCurrentPlayingSpeed(){
        if (player!=null){
            double currentSpeed = player.getCurrentPlayingSpeed();
            if (currentSpeed<1000){
                currentDataPlaySpeedLabel.setText(currentSpeed+"ms/1 data");
            }else {
                currentDataPlaySpeedLabel.setText(currentSpeed/1000+"s/1 data");
            }
        }
    }
    public void refreshTotalTime(){
        if (player!=null){
            double totalTime = player.getTotalTime();
            if (totalTime<1000){
                totalTimeLabel.setText(totalTime+"ms");
            }else {
                totalTimeLabel.setText(totalTime/1000+"s");
            }
        }
    }
    public void refreshCurrentTime(){
        if (player!=null){
            double currentTime = player.getCurrentTime();
            if (currentTime<1000){
                currentTimeLabel.setText(currentTime+"ms");
            }else {
                currentTimeLabel.setText(currentTime/1000+"s");
            }
        }
    }
    public void refreshTimeSlider(){
        int max = timeSlider.getMaximum();
        int currentValue = Integer.valueOf(""+Math.round(max*player.getCurrentTime()/player.getTotalTime()));
        timeSlider.setValue(currentValue);
    }
    private void alertToImportDataFirst(){
        AlertDialog dialog = new AlertDialog("请先导入数据！");
        dialog.showDialog();
    }
    private void alertToLinkSimulationFirst(){
        AlertDialog dialog = new AlertDialog("请先配置模拟舱接口！");
        dialog.showDialog();
    }
    public void playIsOver(){
        resetPlayAndPauseButton();
    }
    public void resetPlayAndPauseButton(){
        playAndPauseButton.setText("播放");
    }
    public void resetTimeSlider(){
        timeSlider.setValue(0);
    }


    public static void main(String[] args) {
        try {
              UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("SCANeR 数据仿真");
        frame.setContentPane(new MainForm().wrapPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //居中显示窗体
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
    }

}
