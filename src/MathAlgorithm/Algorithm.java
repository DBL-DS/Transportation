package MathAlgorithm;

import java.util.ArrayList;
import java.util.RandomAccess;

/**
 * Created by Allen on 2015/3/22.
 */
public class Algorithm {
    public static void main(String[] args) {
        ArrayList<Double> test= new ArrayList<Double>();
        for (int i = 0; i <200 ; i++) {
            test.add(Math.random()) ;
        }
        long startMili=System.currentTimeMillis();
        System.out.println(getZScore(test)[1]);
        long endMili=System.currentTimeMillis();
        System.out.print(endMili-startMili);
    }
    public static double getMax(ArrayList<Double> inputData) {
        if (inputData == null || inputData.size() == 0)
            return -1;
        int len = inputData.size();
        double max = inputData.get(0);
        for (int i = 0; i < len; i++) {
            if (max < inputData.get(i))
                max = inputData.get(i);
        }
        return max;
    }

    /**
     * 求求给定双精度数组中值的最小值
     *
     * @param inputData
     *            输入数据数组
     * @return 运算结果,如果输入值不合法，返回为-1
     */
    public static double getMin(ArrayList<Double> inputData) {
        if (inputData == null || inputData.size() == 0)
            return -1;
        int len = inputData.size();
        double min = inputData.get(0);
        for (int i = 0; i < len; i++) {
            if (min > inputData.get(i))
                min = inputData.get(i);
        }
        return min;
    }

    /**
     * 求给定双精度数组中值的和
     *
     * @param inputData
     *            输入数据数组
     * @return 运算结果
     */
    public static double getSum(ArrayList<Double> inputData) {
        if (inputData == null || inputData.size() == 0)
            return -1;
        int len = inputData.size();
        double sum = 0;
        for (int i = 0; i < len; i++) {
            sum = sum + inputData.get(i);
        }

        return sum;

    }

    /**
     * 求给定双精度数组中值的数目
     *
     * @param
     *     //       Data 输入数据数组
     * @return 运算结果
     */
    public static int getCount(ArrayList<Double> inputData) {
        if (inputData == null)
            return -1;

        return inputData.size();
    }

    /**
     * 求给定双精度数组中值的平均值
     *
     * @param inputData
     *            输入数据数组
     * @return 运算结果
     */
    public static double getAverage(ArrayList<Double> inputData) {
        if (inputData == null || inputData.size() == 0)
            return -1;
        int len = inputData.size();
        double result;
        result = getSum(inputData) / len;

        return result;
    }

    /**
     * 求给定双精度数组中值的平方和
     *
     * @param inputData
     *            输入数据数组
     * @return 运算结果
     */
    public static double getSquareSum(ArrayList<Double> inputData) {
        if (inputData == null || inputData.size() == 0)
            return -1;
        int len = inputData.size();
        double sqrsum = 0.0;
        for (int i = 0; i < len; i++) {
            sqrsum = sqrsum + inputData.get(i) * inputData.get(i);
        }

        return sqrsum;
    }

    /**
     * 求给定双精度数组中值的方差
     *
     * @param inputData
     *            输入数据数组
     * @return 运算结果
     */
    public static double getVariance(ArrayList<Double> inputData) {
        int count = getCount(inputData);
        double sqrsum = getSquareSum(inputData);
        double average = getAverage(inputData);
        double result;
        result = (sqrsum - count * average * average) / count;

        return result;
    }

    /**
     * 求给定双精度数组中值的标准差
     *
     * @param inputData
     *            输入数据数组
     * @return 运算结果
     */
    public static double getStandardDiviation(ArrayList<Double> inputData) {
        double result;
        // 绝对值化很重要
        result = Math.sqrt(Math.abs(getVariance(inputData)));

        return result;

    }
    public static double[] getZScore(ArrayList<Double> inputData) {
        double[] result = new double[inputData.size()];
        // 绝对值化很重要
        for (int i = 0; i < inputData.size() ; i++) {
            result[i] = (inputData.get(i)-getAverage(inputData))/getStandardDiviation(inputData);

        }

        return result;

    }
}
