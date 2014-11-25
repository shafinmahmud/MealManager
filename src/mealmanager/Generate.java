package mealmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author SHAFIN
 */
public class Generate {

    private final Connection connRefG;
    private ResultSet resultsetG;
    private PreparedStatement preparedstatementG;

    /**
     *
     * @param conn
     */
    public Generate(Connection conn) {
        connRefG = conn;
    }


    /**
     *
     * @param tablename
     * @param month
     * @param year
     */
    public void tableDate(String tablename, int month, int year) {
        try {
            String d;
            DateAndTime mon =  new DateAndTime();
            String m = mon.monthName(month - 1);

            // Create a calendar object and set year and moth
            Calendar mycal = new GregorianCalendar(year, month - 1, 1);
            // Get the number of days in that month
            int days = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

            for (int i = 1; i <= days; i++) {
                d = i + "-" + m + "-" + year;

                String sql = "INSERT INTO " + tablename + "(Date) VALUES('" + d + "')";
                preparedstatementG = connRefG.prepareStatement(sql);
                preparedstatementG.execute();
            }

        } catch (SQLException e) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedstatementG != null) {
                    preparedstatementG.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     *
     */
    public void DateCombo() {
        try {
            //creating date combboxes in UI
            String sql = "SELECT Date FROM bazar_history" + Login.monthyear;
            preparedstatementG = connRefG.prepareStatement(sql);
            resultsetG = preparedstatementG.executeQuery();

            while (resultsetG.next()) {
                String date = resultsetG.getString("Date");
                UserInterface.combo_bazar_date.addItem(date);
                UserInterface.combo_meal_date.addItem(date);
                UserInterface.combo_deposite_date.addItem(date);
                UserInterface.combo_depositeEdit_date.addItem(date);
            }

        } catch (SQLException e) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultsetG != null) {
                    resultsetG.close();
                }
                if (preparedstatementG != null) {
                    preparedstatementG.close();
                }
            } catch (SQLException e) {
            }
        }
    }
    
    /**
     * populates member list in profile status table
     */
    public void memberList() {
        
        try {
            String sql = "SELECT firstname AS Name FROM [memberProfile" + Login.monthyear + "]";
            preparedstatementG = connRefG.prepareStatement(sql);
            resultsetG = preparedstatementG.executeQuery();
            UserInterface.table_memberList.setModel(DbUtils.resultSetToTableModel(resultsetG));

        } catch (SQLException ex) {
            Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultsetG != null) {
                    resultsetG.close();
                }
                if (preparedstatementG != null) {
                    preparedstatementG.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     * creating member name combo box
     */
    public void NameCombo() {
        try {
            UpdateData.memberNameList.clear();
            UserInterface.combo_bazar_name.removeAllItems();
            UserInterface.combo_meal_name.removeAllItems();
            UserInterface.combo_deposite_name.removeAllItems();
            UserInterface.combo_depositeEdit_name.removeAllItems();

            UserInterface.combo_bazar_name.addItem("...");
            UserInterface.combo_meal_name.addItem("...");
            UserInterface.combo_deposite_name.addItem("...");
            UserInterface.combo_depositeEdit_name.addItem("...");

            String sql = "SELECT firstname AS Name FROM [memberProfile" + Login.monthyear + "]";

            preparedstatementG = connRefG.prepareStatement(sql);
            resultsetG = preparedstatementG.executeQuery();

            while (resultsetG.next()) {
                String name = resultsetG.getString("Name");
                UpdateData.memberNameList.add(name);
                UserInterface.combo_bazar_name.addItem(name);
                UserInterface.combo_meal_name.addItem(name);
                UserInterface.combo_deposite_name.addItem(name);
                UserInterface.combo_depositeEdit_name.addItem(name);
            }

        } catch (SQLException e) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (resultsetG != null) {
                    resultsetG.close();
                }
                if (preparedstatementG != null) {
                    preparedstatementG.close();
                }
            } catch (SQLException e) {
            }
        }
    }
}
