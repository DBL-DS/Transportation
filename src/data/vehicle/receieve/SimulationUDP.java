package data.vehicle.receieve;

import data.vehicle.structure.SimulatedVehicle;
import data.vehicle.structure.VehicleUDP;

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
            //e.printStackTrace();
            System.out.println("No received data, force to close!");
            return null;
        }
        buffer = packet.getData();
        return byteToSimulatedVehicle(buffer);
    }
    public void close(){
        datagramSocket.close();
    }

    private SimulatedVehicle byteToSimulatedVehicle(byte[] buffer){
        SimulatedVehicle s = new SimulatedVehicle();
        s.setSimulationTime(getFloat(buffer, VehicleUDP.SimulationTime));
        s.setPositionX(getFloat(buffer, VehicleUDP.PositionX));
        s.setPositionY(getFloat(buffer, VehicleUDP.PositionY));
        s.setPositionZ(getFloat(buffer, VehicleUDP.PositionZ));
        s.setSpeed(getFloat(buffer, VehicleUDP.Speed));
        s.setAcceleration(getFloat(buffer, VehicleUDP.Acceleration));
        s.setDriftAngle(getFloat(buffer, VehicleUDP.DriftAngle));
        s.setOffset(getFloat(buffer, VehicleUDP.Offset));
        s.setSteeringWheel(getFloat(buffer, VehicleUDP.SteeringWheel));
        s.setRotationalSpeed(getFloat(buffer, VehicleUDP.RotationalSpeed));
        s.setGasPedal(getFloat(buffer, VehicleUDP.GasPedal));
        s.setBreakPedal(getFloat(buffer, VehicleUDP.BreakPedal));
        s.setClutchPedal(getFloat(buffer, VehicleUDP.ClutchPedal));
        s.setGear(getFloat(buffer,VehicleUDP.Gear));
        s.setRpm(getFloat(buffer, VehicleUDP.Rpm));
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
