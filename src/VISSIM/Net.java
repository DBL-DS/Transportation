package VISSIM;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;

public class Net {
	private ActiveXComponent net;

	public void setNet(ActiveXComponent net) {
		this.net = net;
	}

	public ActiveXComponent getNet() {
		return net;
	}
	
	public Vehicle getVehicle(int i)
	{
		Vehicle out = new Vehicle();
		out.setVehicles(net.invokeGetComponent("Vehicles"));
		out.setVehicle(net.invokeGetComponent("Vehicles").invokeGetComponent(
				"ItemByKey", new Variant(i)));
		return out;
	}

}
