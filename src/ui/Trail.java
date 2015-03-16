package ui;

import data.vehicle.structure.SimulatedVehicle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Hugh on 2015/2/16 0016.
 */
public class Trail {
    private JPanel trailPanel;
    private ChartPanel chartPanel;
    private XYSeries xyseries;
    public Trail()
    {
        trailPanel = new JPanel();
        XYDataset xydataset = createDataSet();
        JFreeChart jfreechart = ChartFactory.createScatterPlot(
                "Trail Curve", "X", "Y", xydataset,
                PlotOrientation.VERTICAL, false, true, false);

        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setAutoRangeIncludesZero(true);
        NumberAxis x=(NumberAxis)xyplot.getDomainAxis();
        x.setAutoRange(true);

        chartPanel = new ChartPanel(jfreechart);
        trailPanel.add(chartPanel);
    }

    public XYDataset createDataSet() {
        xyseries = new XYSeries("");
        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(xyseries);
        return xyseriescollection;
    }
    public void receiveData(SimulatedVehicle simulatedVehicle){
       xyseries.add(simulatedVehicle.getPositionX(),simulatedVehicle.getPositionY());
    }
    public JPanel getTrailPanel() {
        return trailPanel;
    }
    public void refreshSize(Dimension dimension){
        chartPanel.setPreferredSize(dimension);
        chartPanel.setSize(dimension);
        chartPanel.setLocation(0,0);
    }
}
