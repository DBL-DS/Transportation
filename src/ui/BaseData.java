package ui;

import data.structure.SimulatedVehicle;

import javax.swing.*;

/**
 * Created by Hugh on 2015/2/22 0022.
 */
public class BaseData {
    private JPanel baseDataPanel;
    private JTextField velocityTextField;
    private JTextField runningTimeTextField;
    private JTextField acclerationTextField;
    private JTextField laneTextField;
    private JTextField positionTextField;
    private JTextField totalDistanceTextField;
    private JTextField distanceFromCenterTextfield;

    public JPanel getBaseDataPanel() {
        return baseDataPanel;
    }

    public void receiveData(SimulatedVehicle simulatedVehicle){
        velocityTextField.setText(simulatedVehicle.getSpeed()+"km/h");
        if (simulatedVehicle.getSimulationTime()<1000){
            runningTimeTextField.setText(simulatedVehicle.getSimulationTime()+"ms");
        }else {
            runningTimeTextField.setText(simulatedVehicle.getSimulationTime()/1000+"s");
        }
        laneTextField.setText(simulatedVehicle.getLane()+"");
        acclerationTextField.setText(simulatedVehicle.getAcceleration()+" m/(s^2)");
        positionTextField.setText("("+simulatedVehicle.getPositionX()+","
                +simulatedVehicle.getPositionY()+","
                +simulatedVehicle.getPositionZ()+") m");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BaseData");
        frame.setContentPane(new BaseData().baseDataPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
