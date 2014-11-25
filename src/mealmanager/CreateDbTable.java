/*
 */
package mealmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHAFIN
 */
public class CreateDbTable {

    private final Connection connRefCDT;
    private PreparedStatement preparedstatementCDT;
    private final String monthyearTag;

    public CreateDbTable(Connection c, String tag) {

        connRefCDT = c;
        monthyearTag = tag;
    }

    public CreateDbTable(Connection c) {

        connRefCDT = c;
        monthyearTag = null;
    }

    public void member_data() {
        //creating member_data table if not exists
        String sql = "CREATE TABLE IF NOT EXISTS member_data ("
                + "ID INTEGER NOT NULL,"
                + "firstname VARCHAR(45) NOT NULL,"
                + "lastname VARCHAR(45) NULL,"
                + "email VARCHAR(45) NULL,"
                + "PhoneNumber VARCHAR(45) NULL,"
                + "image BLOB NULL,"
                + "PRIMARY KEY (ID));";
        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();

        } catch (SQLException ex) {

        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void month_data() {
        //creating month_data table if not exists
        String sql = "CREATE TABLE IF NOT EXISTS month_data("
                + "AdminID INTEGER NOT NULL,"
                + "username VARCHAR(45) NULL,"
                + "password VARCHAR(45) NULL,"
                + "month VARCHAR(45) NULL,"
                + "year VARCHAR(45) NULL,"
                + "email VARCHAR(45) NULL,"
                + "emailpassword VARCHAR(45) NULL,"
                + "total_member INTEGER NULL,"
                + "total_deposite DOUBLE NULL,"
                + "total_meal DOUBLE NULL,"
                + "total_cost DOUBLE NULL,"
                + "cash_in_hand DOUBLE NULL,"
                + "meal_rate DOUBLE NULL,"
                + " PRIMARY KEY (AdminID));";
        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();
        } catch (SQLException ex) {

        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void BazarHistory(int month, int year) {
        //creating bazar_history_month_year table
        String sql = "CREATE TABLE IF NOT EXISTS bazar_history" + monthyearTag + " ("
                + "Date DATE NOT NULL,"
                + "Time VARCHAR(45) NULL,"
                + "Name VARCHAR(45) NULL,"
                + "Description VARCHAR(45) NULL,"
                + "Cost DOUBLE NULL,"
                + " PRIMARY KEY (Date));";
        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();
        } catch (SQLException ex) {

        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Deposite(int month, int year) {
        //creating deposite_month_year table
        String sql = "CREATE TABLE IF NOT EXISTS deposite" + monthyearTag + "("
                + "TransNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + "Date DATE NOT NULL,"
                + "Time VARCHAR(45) NOT NULL,"
                + "Name VARCHAR(45) NOT NULL,"
                + "Amount DOUBLE NOT NULL);";
        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Mealtable(int month, int year) {
        //creating meal_table_month_year
        String sql = "CREATE TABLE IF NOT EXISTS meal_table" + monthyearTag + "("
                + "Date DATE NOT NULL,"
                + "PRIMARY KEY (Date));";

        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();
        } catch (SQLException ex) {

        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Report(int month, int year) {
        //creating report_month_year
        String sql = "CREATE TABLE IF NOT EXISTS report" + monthyearTag + "("
                + "ID INT NOT NULL,"
                + "Deposite DOUBLE NULL,"
                + "totalMeal FLOAT NULL,"
                + "Expense DOUBLE NULL,"
                + "Balance DOUBLE NULL,"
                + "PRIMARY KEY (ID)"
                + "FOREIGN KEY (ID) REFERENCES member_data(ID));";
        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();
        } catch (SQLException ex) {

        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ReportIDinsertion(int month, int year, int id) {
        //inserting report_ID
        String sql = "INSERT INTO report" + monthyearTag + " (ID) VALUES(" + id + ")";
        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();
        } catch (SQLException ex) {

        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void tempDbTable() {
        //temporary table to store and represent member information

        String sql = "CREATE TABLE IF NOT EXISTS TempReport("
                + "ID INTEGER NOT NULL,"
                + "Name VARCHAR(45) NULL,"
                + "LastName VARCHAR(45) NULL,"
                + "email VARCHAR(45) NULL,"
                + "PhoneNumber VARCHAR(45) NULL,"
                + "Balance DOUBLE NULL,"
                + "image BLOB DEFAULT NULL, "
                + "PRIMARY KEY (ID))";
        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();

        } catch (SQLException ex) {

        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void eraseTepmTable() {
        //deleting data from the temporary table
        String sql = "DELETE FROM TempReport";
        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();

        } catch (SQLException ex) {

        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void viewTable() {

        String sql = "CREATE VIEW  memberProfile" + monthyearTag + " AS "
                + "SELECT report" + monthyearTag + ".ID, member_data.firstname, member_data.lastname,"
                + "member_data.email, member_data.PhoneNumber, report" + monthyearTag + ".Deposite,"
                + "report" + monthyearTag + ".Expense,  report" + monthyearTag + ".totalMeal,  report"
                + monthyearTag + ".Balance, member_data.image FROM  report" + monthyearTag + " INNER JOIN member_data "
                + "ON report" + monthyearTag + ".ID =  member_data.ID ORDER BY  report" + monthyearTag + ".ID";
        try {
            preparedstatementCDT = connRefCDT.prepareStatement(sql);
            preparedstatementCDT.execute();

        } catch (SQLException ex) {

        } finally {
            try {
                if (preparedstatementCDT != null) {
                    preparedstatementCDT.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateDbTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
