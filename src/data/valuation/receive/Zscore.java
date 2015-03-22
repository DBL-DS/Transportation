package data.valuation.receive;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Allen on 2015/3/22.
 */
public class Zscore {
    public static double[] ArrList2Arr(ArrayList<Double> in)
    {
        double[] out = new double[in.size()];
        for (int i = 0; i < in.size() ; i++) {
            out[i] = in.get(i);
        }
        return out;
    }
    public static ArrayList<Double> Arr2ArrList(double[] in)
    {
        ArrayList<Double> out = new ArrayList<Double>();
        for (int i = 0; i < in.length ; i++) {
            out.add(in[i]);
        }
        return out;
    }
}
