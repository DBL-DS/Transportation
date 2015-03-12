package lab;

import data.structure.SimulatedVehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class Lab {
    public static void main(String[] args){
//        ArrayList<String> strings= new ArrayList<String>();
//        strings.add("a");
//        strings.add("b");
//        strings.add("c");
//        strings.add("d");
//        strings.add("e");
//        while (strings.size()!=0){
//          System.out.println(strings.remove(0));
//        }
//        String a = new String("a");
//        a = new String("b");
//        System.out.print(a);
        SimulatedVehicle a = new SimulatedVehicle();
        new Lab().change(a);
        System.out.println(a.getGear());
    }
    public void change(SimulatedVehicle a){
        a.setGear(1);
    }
}
