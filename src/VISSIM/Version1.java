package VISSIM;

import data.vehicle.structure.SimulatedVehicle;


public class Version1 {

	SimulatedVehicle simulatedVehicle;


    public static void main(String[] args) {
		// TODO Auto-generated method stub
		VISSIM vissim = new VISSIM();
		vissim.loadpath("I:\\迅雷下载\\VISSIM510\\3-4.inpx");
		Simulation simulation = vissim.getSimlation();
		int count = 0;
		Net net = vissim.getnet();

        //SimulatedVehicle simulatedVehicle;
		while (count < simulation.getPeriod()*simulation.getRes()) {
			simulation.RunSingle();
			if(count > 114)
			{
				Vehicle vehicle = net.getVehicle(1);
                vehicle.setSpeed(1);

                vehicle.MoveToPosition(10000,1,count/50000);
			}
			count++;

		}

	}

}
