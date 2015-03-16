package VISSIM;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;

public class VISSIM {
	private ActiveXComponent vissim ;
	public VISSIM()
	{
		ComThread.InitMTA();
		vissim = new ActiveXComponent("VISSIM.Vissim.700");
	}
	
	
	public void loadpath(String path)
	{
		vissim.invoke("LoadNet", path);
	}
	
	public Simulation getSimlation()
	{
		Simulation out = new Simulation();
		out.setSimlulation(vissim.invokeGetComponent("Simulation"));
		return out;
		
	}
	public Net getnet()
	{
		Net out = new Net();
		out.setNet(vissim.invokeGetComponent("Net"));
		return out;
	}
    public void exit()
    {
        vissim.safeRelease();
    }

}
