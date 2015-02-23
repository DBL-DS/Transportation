package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel alertTextTextField;

    public AlertDialog(String alertText) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        alertTextTextField.setText(alertText);
    }

    private void onOK() {
// add your code here
        this.setVisible(false);
        dispose();
    }
    public void showDialog(){
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dialogSize = this.getSize();
        this.setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        AlertDialog dialog = new AlertDialog("你好");
        dialog.showDialog();
        dialog = null;
    }
}
