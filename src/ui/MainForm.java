package ui;

import data.structure.SimulatedVehicle;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Hugh on 2015/2/14 0014.
 */
public class MainForm {
    private JPanel wrapPanel;
    private JPanel controlPanel;
    private JLabel timeLabel;
    private JSlider slider1;
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
    private JTextField dataUnitGapTextField;
    private JButton setDataUnitGapButton;
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
                String filePath = filePathTextField.getText();
                player = new Player(filePath);
                if (player.isReadyToPlay()) {
                    AlertDialog dialog = new AlertDialog("读取数据成功！");
                    dialog.showDialog();
                    dialog = null;
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
                if (playAndPauseButton.getText().equals("播放")){
                    if (player.isReadyToPlay()){
                        player.play();
                        playAndPauseButton.setText("暂停");
                    }
                }else {
                    player.pause();
                    playAndPauseButton.setText("播放");
                }
            }
        });
    }

    private void startListenPlayingDataThread(){
        listenPlayingDataThread = new ListenPlayingDataThread(this);
        listenPlayingDataThread.start();
    }
    protected void setUI(SimulatedVehicle simulatedVehicle){
        base.receiveData(simulatedVehicle);
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
