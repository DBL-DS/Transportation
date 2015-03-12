package VISSIM;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class Vehicle {
	private ActiveXComponent Vehicles;
	private ActiveXComponent Vehicle;
	public void setVehicles(ActiveXComponent vehicles) {
		Vehicles = vehicles;
	}
	public ActiveXComponent getVehicles() {
		return Vehicles;
	}
	public void setVehicle(ActiveXComponent vehicle) {
		Vehicle = vehicle;
	}
	public ActiveXComponent getVehicle() {
		return Vehicle;
	}
	public void setSpeed(double speed)
	{
		Dispatch.invoke(Vehicle, "AttValue", Dispatch.Put, new
				 Object[]{"Speed",speed}, new int[1]);
	}

}
