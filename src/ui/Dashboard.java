package ui;

import data.structure.SimulatedVehicle;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.*;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Hugh on 2015/2/22 0022.
 */
public class Dashboard {
    private JPanel dashboardPanel;

    private JFreeChart jFreeChart;
    private ChartPanel chartPanel;
    private DefaultValueDataset dataset;
    private DialPlot dialplot;

    public Dashboard(){
        super();
        dashboardPanel = new JPanel();
        dataset = new DefaultValueDataset(0);
        dialplot = new DialPlot();
        dialplot.setDataset(dataset);
        StandardDialFrame standarddialframe = new StandardDialFrame();
        standarddialframe.setBackgroundPaint(Color.black);
        standarddialframe.setForegroundPaint(Color.darkGray);// 圆边的颜色
        dialplot.setDialFrame(standarddialframe);
        GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(
                255, 255, 255), new Point(), new Color(170, 170, 220));
        DialBackground dialbackground = new DialBackground(gradientpaint);
        dialbackground
                .setGradientPaintTransformer(new StandardGradientPaintTransformer(
                        GradientPaintTransformType.VERTICAL));
        dialplot.setBackground(dialbackground);
        // 设置显示在表盘中央位置的信息
        DialTextAnnotation dialtextannotation = new DialTextAnnotation("KM/H");
        dialtextannotation.setFont(new Font("Dialog", 17, 17));
        dialtextannotation.setRadius(0.6D);// 字体距离圆心的距离
        dialplot.addLayer(dialtextannotation);
        DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
        dialplot.addLayer(dialvalueindicator);
        // 根据表盘的直径大小（0.88），设置总刻度范围
        StandardDialScale standarddialscale = new StandardDialScale(0.0D,
                200.0D, -120.0D, -300.0D, 10D, 9);
        standarddialscale.setTickRadius(0.9D);
        standarddialscale.setTickLabelOffset(0.1D);// 显示数字 距离圆边的距离
        standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
        dialplot.addScale(0, standarddialscale);
        // 设置刻度范围（红色）
        StandardDialRange standarddialrange = new StandardDialRange(0D, 120D,
                Color.green);
        standarddialrange.setInnerRadius(0.6D);
        standarddialrange.setOuterRadius(0.62D);
        dialplot.addLayer(standarddialrange);
        // 设置刻度范围（橘黄色）
        StandardDialRange standarddialrange1 = new StandardDialRange(120D, 180D,
                Color.orange);
        standarddialrange1.setInnerRadius(0.6D);
        standarddialrange1.setOuterRadius(0.62D);
        dialplot.addLayer(standarddialrange1);
        // 设置刻度范围（绿色）
        StandardDialRange standarddialrange2 = new StandardDialRange(180D, 200D,
                Color.red);
        standarddialrange2.setInnerRadius(0.6D);
        standarddialrange2.setOuterRadius(0.62D);
        dialplot.addLayer(standarddialrange2);
        // 设置指针
        org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
        dialplot.addLayer(pointer);
        // 实例化DialCap
        DialCap dialcap = new DialCap();
        dialcap.setRadius(0.1D);
        dialplot.setCap(dialcap);
        jFreeChart = new JFreeChart(dialplot);

        // 设置标题

        jFreeChart.setTitle("速度");
        chartPanel = new ChartPanel(jFreeChart);
        dashboardPanel.add(chartPanel);
    }

    public void receiveData(SimulatedVehicle simulatedVehicle){
        dataset = new DefaultValueDataset(simulatedVehicle.getSpeed());
        dialplot.setDataset(dataset);
    }
    public JPanel getDashboardPanel() {
        return dashboardPanel;
    }
}
