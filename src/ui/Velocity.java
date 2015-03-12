package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import data.structure.SimulatedVehicle;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
/**
 * Created by Hugh on 2015/2/16 0016.
 */
public class Velocity {
    private JPanel velocityPanel;
    private ChartPanel chartPanel;
    private XYSeries xyseries;
    public Velocity()
    {
        initPanel();
        refreshSize();
    }

    public void initPanel(){
        velocityPanel = new JPanel();
        XYDataset xydataset = createDataset();
        JFreeChart jfreechart = ChartFactory.createXYLineChart(
                "Speed Curve", "Time", "Speed", xydataset,
                PlotOrientation.VERTICAL, false, true, false);

        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setAutoRangeIncludesZero(true);
        NumberAxis x=(NumberAxis)xyplot.getDomainAxis();
        x.setAutoRange(true);

        chartPanel = new ChartPanel(jfreechart);
        velocityPanel.add(chartPanel);
    }

    public XYDataset createDataset() {
        xyseries = new XYSeries("");
        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(xyseries);
        return xyseriescollection;
    }
    public void receiveData(SimulatedVehicle simulatedVehicle){
        xyseries.add(simulatedVehicle.getSimulationTime(),simulatedVehicle.getSpeed());
    }
    public JPanel getVelocityPanel() {
        return velocityPanel;
    }
    public void refreshSize(){
        chartPanel.setSize(velocityPanel.getSize());
        chartPanel.setLocation(0,0);

    }
}
