
package mealmanager;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author SHAFIN
 */
public class GraphAndChart {

    private final Connection connRefGAC;
    private ResultSet resultsetGAC;
    private PreparedStatement preparedstatementGAC;

    /**
     * Constructor that needs not database connection
     */
    public GraphAndChart() {
        connRefGAC = null;
    }

    /**
     * Constructor that needs database connection
     * @param c
     */
    public GraphAndChart(Connection c) {
        connRefGAC = c;
    }

    /**
     * Creates Food Habit pie chart
     * @param item
     * @param val
     * @return
     */
    public JFreeChart pieChart(ArrayList<String> item, int[] val) {

        DefaultPieDataset pie = new DefaultPieDataset();

        for (int i = 0; i < item.size(); i++) {
            if (val[i] != 0) {
                pie.setValue(item.get(i), new Integer(val[i]));
            }
        }

        JFreeChart chart = ChartFactory.createPieChart("Food Habits", pie, true, true, true);
        chart.getPlot().setBackgroundPaint(Color.LIGHT_GRAY);
        chart.setBorderVisible(false);

        return chart;
    }

    /**
     * generating and updating Food Habit Chart
     * @param tablename
     * @param wildCard
     * @return
     */
    public int itemCount(String tablename, String wildCard) {
        try {
            String sql = "SELECT COUNT(Description) AS item FROM "
                    + tablename + " WHERE " + wildCard + "";

            preparedstatementGAC = connRefGAC.prepareStatement(sql);
            resultsetGAC = preparedstatementGAC.executeQuery();
            if (resultsetGAC.next()) {
                int item = resultsetGAC.getInt("item");
                return item;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraphAndChart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
