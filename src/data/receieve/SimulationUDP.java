package data.receieve;

import data.structure.SimulatedVehicle;
import data.structure.VehicleUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Hugh on 2015/3/1 0001.
 */
public class SimulationUDP {
    private int bufferSize;
    private int port;
    byte[] buffer;
    DatagramSocket datagramSocket;
    DatagramPacket packet;

    public SimulationUDP(int port) {
        initDefaultData();
        this.port = port;
        initUDP();
    }

    private void initDefaultData(){
        bufferSize = 184;
        buffer = new byte[bufferSize];
    }
    private boolean initUDP(){
        try {
            datagramSocket = new DatagramSocket(port);
            packet = new DatagramPacket(buffer,buffer.length);
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public SimulatedVehicle getData(){
        try {
            datagramSocket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        buffer = packet.getData();
        return byteToSimulatedVehicle(buffer);
    }

    private SimulatedVehicle byteToSimulatedVehicle(byte[] buffer){
        SimulatedVehicle s = new SimulatedVehicle();
        s.setPositionX(getFloat(buffer,VehicleUDP.PositionX));
        s.setPositionY(getFloat(buffer,VehicleUDP.PositionY));
        s.setPositionZ(getFloat(buffer,VehicleUDP.PositionZ));
        s.setSpeed(getFloat(buffer,VehicleUDP.Speed));
        s.setAcceleration(getFloat(buffer,VehicleUDP.Acceleration));
        s.setSteeringWheel(getFloat(buffer,VehicleUDP.SteeringWheel));
        s.setGasPedal(getFloat(buffer,VehicleUDP.GasPedal));
        s.setBreakPedal(getFloat(buffer,VehicleUDP.BreakPedal));
        s.setClutchPedal(getFloat(buffer,VehicleUDP.ClutchPedal));
        s.setRpm(getFloat(buffer,VehicleUDP.Rpm));
        s.setLane(getFloat(buffer,VehicleUDP.Lane));
        s.setSimulationTime(getFloat(buffer,VehicleUDP.SimulationTime));
        s.setLaneLateralShift(getFloat(buffer,VehicleUDP.LaneLateralShift));
        s.setGear(getFloat(buffer,VehicleUDP.Gear));
        return s;
    }
    private float getFloat(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

}
