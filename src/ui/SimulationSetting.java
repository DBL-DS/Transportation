package ui;

import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class SimulationSetting extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField PortTextField;
    private JLabel IPLabel;
    private Player player;

    public SimulationSetting(Player player){
        this.player = player;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setButtonEvent();
        setIPLabel();

    }

    private void onOK() {
// add your code here
        int port = Integer.valueOf(PortTextField.getText());
//        AlertDialog alert;
//        if (isPortOccupied(port)){
//            alert = new AlertDialog("端口被占用！");
//        } else {
//            alert = new AlertDialog("端口设置成功！");
            player.setPort(port);
//        }
//        alert.showDialog();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private void setIPLabel() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = address.getHostAddress();
        IPLabel.setText(ip);
    }

    private void setButtonEvent(){
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private boolean isPortOccupied(int port){
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            return true;
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void showDialog(){
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dialogSize = this.getSize();
        this.setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
        this.setVisible(true);
    }

    public static void main(String[] args){
        Player player = new Player();
        SimulationSetting dialog = new SimulationSetting(player);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
