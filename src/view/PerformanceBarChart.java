package view;

import javax.swing.JDialog;
import model.Performance;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import util.Pair;

class PerformanceBarChart extends JDialog {

  PerformanceBarChart(Performance p) {
    this.setModal(true);
    this.setSize(800, 500);
    setLocationRelativeTo(null);

    double maxVal = 0;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (Pair<String, Double> t : p.getPriceList()) {
      double value = t.getSecond();
      String date = t.getFirst();
      dataset.addValue(value, "", date);
      maxVal = Math.max(maxVal, value);
    }
    JFreeChart chart = ChartFactory.createBarChart(p.getTitle(), "Timestamp", "Value($)", dataset,
        PlotOrientation.VERTICAL, false, false, false
    );

    CategoryPlot categoryPlot = chart.getCategoryPlot();

    categoryPlot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

    categoryPlot.getRangeAxis().setRange(p.getBase(), maxVal + p.getScale());

    ChartPanel panel = new ChartPanel(chart);
    this.add(panel);
  }

}
