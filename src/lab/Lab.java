package lab;

import data.structure.SimulatedVehicle;

import java.util.ArrayList;

/**
 * Created by Hugh on 2015/2/23 0023.
 */
public class Lab {
    public static void main(String[] args){
        ArrayList<String> strings= new ArrayList<String>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        while (strings.size()!=0){
          System.out.println(strings.remove(0));
        }
    }
}
