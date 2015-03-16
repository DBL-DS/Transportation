package ui;

import data.vehicle.structure.SimulatedVehicle;

import javax.swing.*;

/**
 * Created by Hugh on 2015/2/22 0022.
 */
public class BaseData {
    private JPanel baseDataPanel;
    private JTextField velocityTextField;
    private JTextField runningTimeTextField;
    private JTextField accelerationTextField;
    private JTextField laneTextField;
    private JTextField positionTextField;
    private JTextField totalDistanceTextField;
    private JTextField laneLateralShiftTextField;

    public JPanel getBaseDataPanel() {
        return baseDataPanel;
    }

    public void receiveData(SimulatedVehicle simulatedVehicle){
        velocityTextField.setText(formatNum(simulatedVehicle.getSpeed())+"km/h");
        if (simulatedVehicle.getSimulationTime()<1000){
            runningTimeTextField.setText(formatNum(simulatedVehicle.getSimulationTime())+"ms");
        }else {
            runningTimeTextField.setText(formatNum(simulatedVehicle.getSimulationTime()/1000)+"s");
        }
        laneTextField.setText(String.format("%.0f",simulatedVehicle.getLane()));
        accelerationTextField.setText(formatNum(simulatedVehicle.getAcceleration()) + " m/(s^2)");
        positionTextField.setText("("+formatNum(simulatedVehicle.getPositionX())+","
                +formatNum(simulatedVehicle.getPositionY())+","
                +formatNum(simulatedVehicle.getPositionZ())+") m");
        laneLateralShiftTextField.setText(formatNum(simulatedVehicle.getLaneLateralShift()));
    }

    private String formatNum(double num){
        return String.format("%.3f",num);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BaseData");
        frame.setContentPane(new BaseData().baseDataPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
