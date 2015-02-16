package ui;

import javax.swing.*;

/**
 * Created by Hugh on 2015/2/14 0014.
 */
public class MainForm {
    private JPanel wrapPanel;
    private JPanel controlPanel;
    private JLabel timeLabel;
    private JSlider slider1;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().wrapPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
