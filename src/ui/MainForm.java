package ui;

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
    private JPanel getDataPanel;
    private JButton getDataFromSimulationButton;
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
    private JLabel separatorLabel;
    private JTextField dataUnitTimeGapTextField;
    private JButton setDataUnitTimeGapButton;
    private JLabel dataUnitTimeGapLabel;
    private JLabel speedLabel;
    private JLabel currentDataPlaySpeedLabel;
    private JLabel totalTimeLabel;
    private JLabel currentTimeLabel;
    private JPanel statusPanel;
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

    public MainForm() {
        initViewPanels();
        setTransformViewPanelButtonEvent();
        setControlPanelEvent();
        startListenPlayingDataThread();
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

    private void setControlPanelEvent(){
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
                player = new Player(filePath);
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
                            player.play();
                            playAndPauseButton.setText("暂停");
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

    private void startListenPlayingDataThread(){
        listenPlayingDataThread = new ListenPlayingDataThread(this);
        listenPlayingDataThread.start();
    }
    protected void setUI(SimulatedVehicle simulatedVehicle){
        refreshStatusPanel();
        refreshTimeSlider();
        base.receiveData(simulatedVehicle);
        dashboard.receiveData(simulatedVehicle);
        velocity.receiveData(simulatedVehicle);
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
    public void alertToImportDataFirst(){
        AlertDialog dialog = new AlertDialog("请先导入数据！");
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
