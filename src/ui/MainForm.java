package ui;

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
    private JButton startAndPauseButton;
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
    private JPanel base;
    private JPanel trail;
    private JPanel lane;
    private JPanel velocity;
    private JPanel acceleration;
    private JPanel dashboard;
    private JPanel evaluation;
    private JPanel now;
    private JPanel welcome;
    private Player player;

    public MainForm() {
        initViewPanels();
        setTransformViewPanelButtonEvent();
        setControlPanelEvent();
    }

    private void initViewPanels(){
        base = new BaseData().getBaseDataPanel();
        trail = new Trail().getTrailPanel();
        lane = new Lane().getLanePanel();
        velocity = new Velocity().getVelocityPanel();
        acceleration = new Acceleration().getAccelerationPanel();
        dashboard = new Dashboard().getDashboardPanel();
        evaluation = new Evaluation().getEvaluationPanel();
        welcome = new Welcome().getWelcomePanel();

        transformViewPanel(welcome);
    }
    private void setTransformViewPanelButtonEvent(){
        baseDataButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(base);
            }
        });
        trailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(trail);
            }
        });
        laneButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(lane);
            }
        });
        velocityButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(velocity);
            }
        });
        accelerationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(acceleration);
            }
        });
        dashboardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(dashboard);
            }
        });
        evaluationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transformViewPanel(evaluation);
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
                System.out.print(this.getClass().getName());
            }
        });

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
