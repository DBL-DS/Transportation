package VISSIM;

import com.jacob.activeX.ActiveXComponent;

public class Simulation {
	private ActiveXComponent simulation;

	public void setSimlulation(ActiveXComponent simlulation) {
		this.simulation = simlulation;
	}

	public ActiveXComponent getSimlulation() {
		return simulation;
	}
	
	

	public int getPeriod() {
		return simulation.invoke("AttValue", "SimPeriod").toInt();
	}

	

	public int getRes() {
		return simulation.invoke("AttValue", "SimRes").toInt();
	}

	private int Period;
	private int Res;
	
	public void RunSingle(){
		simulation.invoke("RunSingleStep");
	}
	public void RunCon()
	{
		simulation.invoke("RunContinuous");
	}

}
