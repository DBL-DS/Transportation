package data.structure;

/**
 * Created by Hugh on 2015/2/14 0014.
 */
public class SimulatedVehicle {
    private double positionX;
    private double positionY;
    private double positionZ;
    private double speed;
    private double acceleration;
    private double steeringWheel;
    private double gasPedal;
    private double breakPedal;
    private double clutchPedal;
    private double rpm;
    private double lane;
    private double simulationTime;
    private double laneLateralShift;
    private double gear;
    private long lastSaveTime;

    public SimulatedVehicle() {
        lastSaveTime = 0;
    }

    public long getLastSaveTime() {
        return lastSaveTime;
    }

    public void setLastSaveTime(long lastSaveTime) {
        this.lastSaveTime = lastSaveTime;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionZ() {
        return positionZ;
    }

    public void setPositionZ(double positionZ) {
        this.positionZ = positionZ;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(double steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    public double getGasPedal() {
        return gasPedal;
    }

    public void setGasPedal(double gasPedal) {
        this.gasPedal = gasPedal;
    }

    public double getBreakPedal() {
        return breakPedal;
    }

    public void setBreakPedal(double breakPedal) {
        this.breakPedal = breakPedal;
    }

    public double getClutchPedal() {
        return clutchPedal;
    }

    public void setClutchPedal(double clutchPedal) {
        this.clutchPedal = clutchPedal;
    }

    public double getRpm() {
        return rpm;
    }

    public void setRpm(double rpm) {
        this.rpm = rpm;
    }

    public double getLane() {
        return lane;
    }

    public void setLane(double lane) {
        this.lane = lane;
    }

    public double getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(double simulationTime) {
        this.simulationTime = simulationTime;
    }

    public double getLaneLateralShift() {
        return laneLateralShift;
    }

    public void setLaneLateralShift(double laneLateralShift) {
        this.laneLateralShift = laneLateralShift;
    }

    public double getGear() {
        return gear;
    }

    public void setGear(double gear) {
        this.gear = gear;
    }
}
