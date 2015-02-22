package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton dashboradButton;
    private JPanel exporDataPanel;
    private JButton setStartPointButton;
    private JButton setEndPointButton;
    private JButton exportDataButton;
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

    public MainForm() {
        filePathChooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnVal = fileChooser.showOpenDialog(fileChooser);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    filePathTextField.setText(filePath);
                }
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
    }

}
