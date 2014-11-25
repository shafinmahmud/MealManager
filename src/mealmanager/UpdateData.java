/*
 */
package mealmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author SHAFIN
 */
public class UpdateData {

    private final Connection connRefUD;
    private ResultSet resultsetUD;
    private PreparedStatement preparedstatementUD;

    private final String monthyearTagUD;
    static String AdminID;
    static ArrayList<String> memberNameList = new ArrayList<>();
    static String sql = null;

    static double total_cost = 0;
    static double total_dep = 0;
    static double itotal_dep = 0;
    static double total_meal = 0;
    static double itotal_meal = 0;
    static double mealRate = 0;
    static double iExpense = 0;
    static double iDeposite = 0;
    static double iBalance = 0;
    static double cashInHand = 0;
    
    static JFreeChart chart;

    /**
     *
     * @param conn
     * @param tag
     */
    public UpdateData(Connection conn, String tag) {
        connRefUD = conn;
        monthyearTagUD = tag;
    }

    /**
     * return AdminID
     * @param m
     * @param y
     */
    public static void setAdminID(int m, int y) {
        AdminID = y + "" + m;
    }

    /**
     *
     */
    public void BazarTable() {
        try {
            //updating total cost from bazarhistory
            sql = "SELECT SUM(cost) AS Total FROM bazar_history" + monthyearTagUD;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();

            if (resultsetUD.next()) {
                total_cost = resultsetUD.getDouble("total");
                UserInterface.txt_totalBazar.setText(String.format("%.2f", total_cost));
            }

            //updating total cost into report table
            sql = "UPDATE month_data SET total_cost='" + total_cost + "'WHERE AdminID='" + AdminID + "'";

            preparedstatementUD = connRefUD.prepareStatement(sql);
            preparedstatementUD.execute();

            //refreshing table_Bazar
            sql = "SELECT Date,Name,Description,Cost FROM bazar_history" + monthyearTagUD;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();
            UserInterface.table_Bazar.setModel(DbUtils.resultSetToTableModel(resultsetUD));

        } catch (SQLException e) {
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultsetUD != null) {
                    resultsetUD.close();
                }
                if (preparedstatementUD != null) {
                    preparedstatementUD.close();
                }
            } catch (SQLException e) {
            }

        }
    }

    /**
     *
     */
    public void MealTable() {
        try {

            //updating table_Meal
            sql = "SELECT * FROM meal_table" + monthyearTagUD;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();
            UserInterface.table_Meal.setModel(DbUtils.resultSetToTableModel(resultsetUD));

            //calculating total meal of person and udating this data in report table
            for (String mname : memberNameList) {

                sql = "SELECT SUM(" + mname + ") AS total FROM meal_table" + monthyearTagUD;
                preparedstatementUD = connRefUD.prepareStatement(sql);
                resultsetUD = preparedstatementUD.executeQuery();
                if (resultsetUD.next()) {
                    itotal_meal = resultsetUD.getDouble("total");
                }

                sql = "SELECT ID FROM member_data WHERE firstname = '" + mname + "'";
                preparedstatementUD = connRefUD.prepareStatement(sql);
                resultsetUD = preparedstatementUD.executeQuery();
                if (resultsetUD.next()) {
                    int id = resultsetUD.getInt("ID");

                    sql = "UPDATE report" + monthyearTagUD + " SET totalMeal=" + itotal_meal + " WHERE ID=" + id;
                    preparedstatementUD = connRefUD.prepareStatement(sql);
                    preparedstatementUD.execute();
                }
            }

            //calculationg total meal for a month and updating report table
            sql = "SELECT SUM(totalMeal) AS total FROM report" + monthyearTagUD;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();
            resultsetUD.next();
            total_meal = resultsetUD.getDouble("total");

            sql = "UPDATE month_data SET total_meal=" + total_meal + " WHERE AdminID='" + AdminID + "'";
            preparedstatementUD = connRefUD.prepareStatement(sql);
            preparedstatementUD.execute();

            UserInterface.txt_totalMeal.setText(String.format("%.2f", total_meal));
            UserInterface.txt_totalMeal_report.setText(String.format("%.2f", total_meal));

        } catch (SQLException e) {
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultsetUD != null) {
                    resultsetUD.close();
                }
                if (preparedstatementUD != null) {
                    preparedstatementUD.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     *
     */
    public void DepositeTable() {
        try {
            //updating deposite table
            sql = "SELECT * FROM deposite" + monthyearTagUD + " ORDER BY Date";
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();
            UserInterface.table_Deposite.setModel(DbUtils.resultSetToTableModel(resultsetUD));

            //Calculating sum of total deposite
            sql = "SELECT SUM(Amount) AS total FROM deposite" + monthyearTagUD;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();
            resultsetUD.next();
            total_dep = resultsetUD.getDouble("total");

            sql = "UPDATE month_data SET total_deposite=" + total_dep + " WHERE AdminID='" + AdminID + "'";
            preparedstatementUD = connRefUD.prepareStatement(sql);
            preparedstatementUD.execute();

            //calculating individual total deposite of members and udating this data in report table
            for (String mname : memberNameList) {

                sql = "SELECT SUM(Amount) AS total FROM deposite" + monthyearTagUD + " WHERE Name='" + mname + "'";
                preparedstatementUD = connRefUD.prepareStatement(sql);
                resultsetUD = preparedstatementUD.executeQuery();
                resultsetUD.next();
                itotal_dep = resultsetUD.getDouble("total");

                sql = "SELECT ID FROM member_data WHERE firstname = '" + mname + "'";
                preparedstatementUD = connRefUD.prepareStatement(sql);
                resultsetUD = preparedstatementUD.executeQuery();
                resultsetUD.next();
                int id = resultsetUD.getInt("ID");

                sql = "UPDATE report" + monthyearTagUD + " SET Deposite=" + itotal_dep + " WHERE ID=" + id;
                preparedstatementUD = connRefUD.prepareStatement(sql);
                preparedstatementUD.execute();

            }

        } catch (SQLException e) {
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultsetUD != null) {
                    resultsetUD.close();
                }
                if (preparedstatementUD != null) {
                    preparedstatementUD.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     *
     */
    public void report() {
        try {

            //retriving total deposite from report table
            sql = "SELECT total_deposite AS dep FROM month_data WHERE AdminID=" + AdminID;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();
            if (resultsetUD.next()) {
                total_dep = resultsetUD.getDouble("dep");
            }

            UserInterface.txt_report_totaldeposite.setText(String.format("%.2f", total_dep));
            UserInterface.txt_depositeHistory_totalDeposite.setText(String.format("%.2f", total_dep));

            //retriving total cost from report table
            sql = "SELECT total_cost AS cost FROM month_data WHERE AdminID=" + AdminID;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();
            if (resultsetUD.next()) {
                total_cost = resultsetUD.getDouble("cost");
            }

            cashInHand = total_dep - total_cost;

            //updating the cashInHand in report table
            sql = "UPDATE month_data SET cash_in_hand=" + cashInHand + " WHERE AdminID=" + AdminID;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            preparedstatementUD.execute();

            UserInterface.txt_report_cInHand.setText(String.format("%.2f", cashInHand));

            //calculating and updating meal rate
            if (total_meal != 0) {
                mealRate = total_cost / total_meal;
            } else {
                mealRate = 0;
            }

            sql = "UPDATE month_data SET meal_rate=" + mealRate + " WHERE  AdminID=" + AdminID;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            preparedstatementUD.execute();

            UserInterface.txt_report_mrate.setText(String.format("%.2f", mealRate));

            //calculating individual expenses and update in report
            for (String mname : memberNameList) {
                //finding related id for the name
                sql = "SELECT ID FROM member_data WHERE firstname = '" + mname + "'";
                preparedstatementUD = connRefUD.prepareStatement(sql);
                resultsetUD = preparedstatementUD.executeQuery();
                if (resultsetUD.next()) {
                    int id = resultsetUD.getInt("ID");

                    //projecting data from that id from the report table
                    sql = "SELECT Deposite, totalMeal, Expense, Balance FROM report" + monthyearTagUD + " WHERE ID=" + id;
                    preparedstatementUD = connRefUD.prepareStatement(sql);
                    resultsetUD = preparedstatementUD.executeQuery();

                    if (resultsetUD.next()) {
                        itotal_meal = resultsetUD.getDouble("totalMeal");
                        iDeposite = resultsetUD.getDouble("Deposite");

                        iExpense = itotal_meal * mealRate;
                        iBalance = iDeposite - iExpense;

                        sql = "UPDATE report" + monthyearTagUD + " SET Expense=" + String.format("%.2f", iExpense) + ", Balance=" + String.format("%.2f", iBalance) + " WHERE ID=" + id;
                        preparedstatementUD = connRefUD.prepareStatement(sql);
                        preparedstatementUD.execute();
                    }
                }

            }

            //updating the userinterface table of report
            sql = "SELECT  member_data.ID, member_data.firstname AS Name, "
                    + "report" + monthyearTagUD + ".Deposite, report" + monthyearTagUD + ".totalMeal, "
                    + "report" + monthyearTagUD + ".Expense,  report" + monthyearTagUD + ".Balance  "
                    + "FROM  report" + monthyearTagUD + " INNER JOIN member_data "
                    + "ON report" + monthyearTagUD + ".ID =  member_data.ID "
                    + "ORDER BY  report" + monthyearTagUD + ".ID";
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();
            UserInterface.table_Report.setModel(DbUtils.resultSetToTableModel(resultsetUD));

            //Pie Ploting of Food Habit
            ArrayList<String> item = new ArrayList<>();
            GraphAndChart graph = new GraphAndChart(connRefUD);
            int[] val = new int[10];

            String wildcard = "Description LIKE '%chic%' OR  Description LIKE '%murg%'";
            val[0] = graph.itemCount("bazar_history" + monthyearTagUD + "", wildcard);
            item.add("Chicken");

            wildcard = "Description LIKE '%bee%' OR  Description LIKE '%goru%'";
            val[1] = graph.itemCount("bazar_history" + monthyearTagUD + "", wildcard);
            item.add("Beef");

            wildcard = "Description LIKE '%fish%' OR  Description LIKE '%mach%'";
            val[2] = graph.itemCount("bazar_history" + monthyearTagUD + "", wildcard);
            item.add("Fish");

            wildcard = "Description LIKE '%egg%' OR  Description LIKE '%dee%' OR  Description LIKE '%dim%'";
            val[3] = graph.itemCount("bazar_history" + monthyearTagUD + "", wildcard);
            item.add("Egg");

            wildcard = "Description LIKE 'veg' OR  Description LIKE 'vegetable' OR  Description LIKE '%sobji'";
            val[4] = graph.itemCount("bazar_history" + monthyearTagUD + "", wildcard);
            item.add("Vegetable");

            chart = graph.pieChart(item, val);
            JPanel chartpanel = new ChartPanel(chart);
            chartpanel.setPreferredSize(new Dimension(420, 450));

            UserInterface.panel.removeAll();
            UserInterface.panel.add(chartpanel);

            chartpanel.setVisible(true);
            UserInterface.panel.repaint();
            UserInterface.panel.setVisible(true);
            UserInterface.panel.revalidate();

        } catch (SQLException e) {
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultsetUD != null) {
                    resultsetUD.close();
                }
                if (preparedstatementUD != null) {
                    preparedstatementUD.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     *
     */
    public void totalMember() {
        try {
            int totalMember;
            //counting total member for the month
            sql = "SELECT COUNT(ID) AS ID FROM report" + monthyearTagUD + "";
            preparedstatementUD = connRefUD.prepareStatement(sql);
            resultsetUD = preparedstatementUD.executeQuery();

            if (resultsetUD.next()) {
                totalMember = resultsetUD.getInt("ID");
            } else {
                totalMember = 0;
            }
            sql = "UPDATE month_data SET total_member=" + totalMember + " WHERE AdminID=" + AdminID;
            preparedstatementUD = connRefUD.prepareStatement(sql);
            preparedstatementUD.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultsetUD != null) {
                    resultsetUD.close();
                }
                if (preparedstatementUD != null) {
                    preparedstatementUD.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     *
     * @param id
     */
    public void MemberProfile(int id) {
        try {
            if (id > 500) {
                sql = "SELECT firstname, lastname, email, PhoneNumber, Deposite, totalMeal, Expense, Balance FROM [memberProfile" + monthyearTagUD + "] WHERE ID=" + id;
                preparedstatementUD = connRefUD.prepareStatement(sql);
                resultsetUD = preparedstatementUD.executeQuery();
                if (resultsetUD.next()) {

                    UserInterface.txt_p_memberId.setText(String.format("%d", id));

                    String name = resultsetUD.getString("firstname");
                    String lname = resultsetUD.getString("lastname");
                    String val = name + " " + lname;
                    UserInterface.txt_p_name.setText(val);

                    val = resultsetUD.getString("email");
                    UserInterface.txt_p_email.setText(val);

                    val = resultsetUD.getString("PhoneNumber");
                    UserInterface.txt_p_phnNumber.setText(val);

                    double deposite = resultsetUD.getDouble("Deposite");
                    UserInterface.txt_p_deposite.setText(String.format("%.2f", deposite));

                    val = resultsetUD.getString("totalMeal");
                    UserInterface.txt_p_meal.setText(val);

                    double expense = resultsetUD.getDouble("Expense");
                    UserInterface.txt_p_consumed.setText(String.format("%.2f", expense));

                    double balance = resultsetUD.getDouble("Balance");
                    UserInterface.txt_p_balance.setText(String.format("%.2f", balance));

                    int barVal;
                    if ((deposite >= balance) && deposite > 0 && balance > 0) {
                        barVal = (int) ((balance / deposite) * 100);

                    } else {
                        barVal = 0;
                    }

                    if (barVal <= 25) {
                        UserInterface.balance_progressBar.setValue(barVal);
                        UserInterface.balance_progressBar.setForeground(Color.red);
                    } else {
                        UserInterface.balance_progressBar.setValue(barVal);
                        UserInterface.balance_progressBar.setForeground(Color.green);
                    }

                    sql = "SELECT Date, Name, Description, Cost FROM bazar_history" + monthyearTagUD + " WHERE Name = '" + name + "'";
                    preparedstatementUD = connRefUD.prepareStatement(sql);
                    resultsetUD = preparedstatementUD.executeQuery();
                    UserInterface.table_p_bazarHistory.setModel(DbUtils.resultSetToTableModel(resultsetUD));

                    sql = "SELECT Date, Time, Name, Amount FROM deposite" + monthyearTagUD + " WHERE Name ='" + name + "'";
                    preparedstatementUD = connRefUD.prepareStatement(sql);
                    resultsetUD = preparedstatementUD.executeQuery();
                    UserInterface.tabel_p_deposite.setModel(DbUtils.resultSetToTableModel(resultsetUD));

                }

            }

        } catch (SQLException e) {
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultsetUD != null) {
                    resultsetUD.close();
                }
                if (preparedstatementUD != null) {
                    preparedstatementUD.close();
                }
            } catch (SQLException e) {
            }
        }
    }

}
