package data.vehicle.structure;

/**
 * Created by Hugh on 2015/2/14 0014.
 * 重要参数：
 *          driftAngle（偏航角）：车头方向与道路中心线的夹角——利用模拟舱现有函数间接获取
 *          offset（侧向偏位）：距离道路中心线的距离
 *          rotationalSpeed(方向盘转速)：单位时间内方向盘转动的角度——利用模拟舱现有函数间接获取
 */
public class SimulatedVehicle {
    private double simulationTime;
    private double positionX;
    private double positionY;
    private double positionZ;
    private double speed;
    private double acceleration;
    private double driftAngle;
    private double offset;
    private double steeringWheel;
    private double rotationalSpeed;
    private double gasPedal;
    private double breakPedal;
    private double clutchPedal;
    private double gear;
    private double rpm;

    public double getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(double simulationTime) {
        this.simulationTime = simulationTime;
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

    public double getDriftAngle() {
        return driftAngle;
    }

    public void setDriftAngle(double driftAngle) {
        this.driftAngle = driftAngle;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public double getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(double steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    public double getRotationalSpeed() {
        return rotationalSpeed;
    }

    public void setRotationalSpeed(double rotationalSpeed) {
        this.rotationalSpeed = rotationalSpeed;
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

    public double getGear() {
        return gear;
    }

    public void setGear(double gear) {
        this.gear = gear;
    }

    public double getRpm() {
        return rpm;
    }

    public void setRpm(double rpm) {
        this.rpm = rpm;
    }
}
