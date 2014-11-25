
package mealmanager;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author SHAFIN
 */
public class ModifyMember {

    private final Connection connRefMM;
    private PreparedStatement preparedstatementMM;
    private ResultSet resultsetMM;

    /**
     *
     * @param conn
     */
    public ModifyMember(Connection conn) {
        connRefMM = conn;
    }

    /**
     *
     */
    public void listing() {
        try {
            String sql = "SELECT ID AS ID, (Name||' '||LastName) AS Name FROM TempReport ORDER BY ID";
            preparedstatementMM = connRefMM.prepareStatement(sql);
            resultsetMM = preparedstatementMM.executeQuery();

            NewMonthInitializer.table_import_member.setModel(DbUtils.resultSetToTableModel(resultsetMM));
        } catch (SQLException ex) {
            Logger.getLogger(ModifyMember.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (resultsetMM != null) {
                    resultsetMM.close();
                }
                if (preparedstatementMM != null) {
                    preparedstatementMM.close();
                }
            } catch (SQLException e) {
            }
        }

    }

    /**
     *
     * @param id
     * @param lastname
     * @param email
     * @param phonenumber
     */
    public void editMemberCredential(int id, String lastname, String email, String phonenumber) {
        try {
            String sql = "UPDATE member_data SET lastname='" + lastname + "', email='" + email + "', PhoneNumber='" + phonenumber
                    + "'WHERE ID=" + id;
            preparedstatementMM = connRefMM.prepareStatement(sql);
            preparedstatementMM.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ModifyMember.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (preparedstatementMM != null) {
                    preparedstatementMM.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     *
     * @param id
     * @param name
     * @param lastname
     * @param email
     * @param phonenumber
     * @param balance
     */
    public void tempInsertNewMember(int id, String name, String lastname, String email, String phonenumber, double balance) {

        try {
            //inserting data into tempreport
            String sql = "INSERT INTO TempReport(ID, Name, LastName, email, PhoneNumber, Balance) "
                    + "VALUES(" + id + ",'" + name + "', '" + lastname + "', '" + email
                    + "', '" + phonenumber + "'," + balance + ")";
            preparedstatementMM = connRefMM.prepareStatement(sql);
            preparedstatementMM.execute();

        } catch (SQLException | HeadlessException ex) {
            Logger.getLogger(ModifyMember.class.getName()).log(Level.SEVERE, null, ex);
            // JOptionPane.showMessageDialog(null, ex);
        }finally{
            try {
                if (preparedstatementMM != null) {
                    preparedstatementMM.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     *
     * @param monthYear
     * @param id
     * @param name
     * @param lastname
     * @param email
     * @param phonenumber
     * @param balance
     */
    public void insertNewMember(String monthYear, int id, String name, String lastname, String email, String phonenumber, Double balance) {

        try {
            //checking id into member_data
            String sql = "SELECT ID FROM member_data WHERE ID=" + id;
            preparedstatementMM = connRefMM.prepareStatement(sql);
            resultsetMM = preparedstatementMM.executeQuery();

            if (!resultsetMM.next()) {
                //inserting data into member_data
                sql = "INSERT INTO member_data(ID, firstname, lastname, email, PhoneNumber) "
                        + "VALUES(" + id + ",'" + name + "', '" + lastname + "', '" + email
                        + "', '" + phonenumber + "')";

                preparedstatementMM = connRefMM.prepareStatement(sql);
                preparedstatementMM.execute();
            }

            //inserting data into report
            sql = "INSERT INTO report" + monthYear + " (ID, Balance) VALUES(" + id + ", " + balance + ")";
            preparedstatementMM = connRefMM.prepareStatement(sql);
            preparedstatementMM.execute();

            //inserting column into the meal_table
            //String posName = UpdateInfo.memberNameList.get(UpdateInfo.memberNameList.size() - 1);
            sql = "ALTER TABLE meal_table" + monthYear + " ADD COLUMN " + name + " FLOAT NULL ";
            preparedstatementMM = connRefMM.prepareStatement(sql);
            preparedstatementMM.execute();

        } catch (SQLException | HeadlessException e) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                if (resultsetMM != null) {
                    resultsetMM.close();
                }
                if (preparedstatementMM != null) {
                    preparedstatementMM.close();
                }
            } catch (SQLException e) {
            }
        }
    }
}
