package ui;

import data.history.structure.DataHistory;
import data.vehicle.receieve.SimulationReadFile;
import data.vehicle.structure.SimulatedVehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Hugh on 2015/3/21 0021.
 */
public class History {
    private JPanel historyPanel;
    private JTable historyTable;
    private JScrollPane historyScrollPane;
    private DefaultTableModel historyModel;
    private int index;

    public History() {
        init();
    }

    private void init(){
        index = 1;
    }

    public void setModel(){
        historyModel = new DefaultTableModel(new Object[][]{},new String[]{
            "序号","保存日期","Hash值","选择"
        });
        historyTable.setModel(historyModel);
        historyTable.getColumnModel().getColumn(0).setMaxWidth(50);
        historyScrollPane.getViewport().setBackground(new Color(255,255,255));
    }
    public void addRow(DataHistory data){
        Object rowData[] = {index++,data.getSaveTime(),data.getHashCode(),1};
        historyModel.addRow(rowData);
    }
    public JPanel getHistoryPanel(){
        return historyPanel;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("History");
        History history = new History();
        history.setModel();
        ArrayList<SimulatedVehicle> vehicles = new SimulationReadFile().getListFromCsvFile("./csvFile/standard.csv");
        history.addRow(new DataHistory(vehicles));
        frame.setContentPane(history.historyPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
