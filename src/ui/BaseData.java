package ui;

import javax.swing.*;

/**
 * Created by Hugh on 2015/2/22 0022.
 */
public class BaseData {
    private JPanel baseDataPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;

    public JPanel getBaseDataPanel() {
        return baseDataPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BaseData");
        frame.setContentPane(new BaseData().baseDataPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
