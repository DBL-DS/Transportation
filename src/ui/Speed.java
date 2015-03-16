package ui;

import javax.swing.*;

import data.vehicle.structure.SimulatedVehicle;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

/**
 * Created by Hugh on 2015/2/16 0016.
 */
public class Speed {
    private JPanel speedPanel;
    private ChartPanel chartPanel;
    private XYSeries xyseries;
    public Speed()
    {
        initPanel();
    }

    public void initPanel(){
        speedPanel = new JPanel();
        XYDataset xydataset = createDataSet();
        JFreeChart jfreechart = ChartFactory.createXYLineChart(
                "Speed Curve", "Time", "Speed", xydataset,
                PlotOrientation.VERTICAL, false, true, false);

        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setAutoRangeIncludesZero(true);
        NumberAxis x=(NumberAxis)xyplot.getDomainAxis();
        x.setAutoRange(true);

        chartPanel = new ChartPanel(jfreechart);
        speedPanel.add(chartPanel);
    }

    public XYDataset createDataSet() {
        xyseries = new XYSeries("");
        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(xyseries);
        return xyseriescollection;
    }
    public void receiveData(SimulatedVehicle simulatedVehicle){
        xyseries.add(simulatedVehicle.getSimulationTime(),simulatedVehicle.getSpeed());
    }
    public JPanel getSpeedPanel() {
        return speedPanel;
    }
    public void refreshSize(Dimension dimension){
        chartPanel.setPreferredSize(dimension);
        chartPanel.setSize(dimension);
        chartPanel.setLocation(0,0);
    }

}
