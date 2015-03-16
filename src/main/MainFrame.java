package main;

import ui.MainForm;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Hugh on 2015/2/25 0025.
 */
public class MainFrame {
    JFrame mainFrame;

    public MainFrame() {
        initFrame();
    }

    private void initFrame(){
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
        mainFrame = new JFrame("SCANeR 数据仿真");
        mainFrame.setContentPane(new MainForm().getWrapPanel());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = mainFrame.getSize();
        mainFrame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }
    public void showFrame(){
        mainFrame.setVisible(true);
    }
}
