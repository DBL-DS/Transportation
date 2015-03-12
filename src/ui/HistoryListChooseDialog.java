package ui;

import data.structure.VehicleHistory;
import player.Player;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.util.ArrayList;

public class HistoryListChooseDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable historyTable;
    private Player player;
    public static final int Column = 4;

    public HistoryListChooseDialog(Player player) {
        this.player = player;
        defaultInit();
    }

    private void defaultInit(){
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        historyTable = new JTable(new HistoryTable(player.getHistoryList()));
    }

    public static void main(String[] args) {
        Player player1 = new Player();
        player1.readDataFromFile("C:\\Users\\Hugh\\Desktop\\20150305_163022.csv");
        HistoryListChooseDialog dialog = new HistoryListChooseDialog(player1);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

class HistoryTable extends AbstractTableModel{
    private String[] columnNames = {"","HashCode","Save Time",""};
    private Object[][] data;

    public HistoryTable(ArrayList<VehicleHistory> historyList) {
        data = new Object[historyList.size()][HistoryListChooseDialog.Column];
        for (int i = 0; i < historyList.size(); i++) {
            data[i][0] = i+1;
            data[i][1] = historyList.get(i).getHashCode();
            data[i][2] = historyList.get(i).getSaveTime();
        }
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
