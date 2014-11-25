package mealmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHAFIN
 */
public class ID {

    private final Connection connRefID;
    private ResultSet resultsetID;
    private PreparedStatement preparedstatementID;

    /**
     *
     * @param conn
     */
    public ID(Connection conn) {
        connRefID = conn;
    }

    /**
     *
     * @return
     */
    public int memberID() {

        try {
            for (int i = 501; i < 599; i++) {
                String sql = "SELECT COUNT(*) AS ID "
                        + "FROM member_data "
                        + "WHERE ID LIKE " + i;

                preparedstatementID = connRefID.prepareStatement(sql);
                resultsetID = preparedstatementID.executeQuery();
                resultsetID.next();
                int validation = resultsetID.getInt("ID");

                if (validation == 0) {
                    return i;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultsetID != null) {
                    resultsetID.close();
                }
                if (preparedstatementID != null) {
                    preparedstatementID.close();
                }
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    /**
     *
     * @param id
     * @return
     */
    public int tempID(int id) {

        try {
            String sql = "SELECT COUNT(*) AS ID "
                    + "FROM TempReport "
                    + "WHERE ID LIKE " + id;
            preparedstatementID = connRefID.prepareStatement(sql);
            resultsetID = preparedstatementID.executeQuery();
            resultsetID.next();
            int idVal = resultsetID.getInt("ID");
            if (idVal != 0) {
                for (int i = id; i < 599; i++) {
                    sql = "SELECT COUNT(*) AS ID "
                            + "FROM TempReport "
                            + "WHERE ID LIKE " + i;
                    System.out.println(i);
                    preparedstatementID = connRefID.prepareStatement(sql);
                    resultsetID = preparedstatementID.executeQuery();
                    resultsetID.next();
                    int validation = resultsetID.getInt("ID");

                    if (validation == 0) {
                        return i;
                    }
                }
            } else {
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultsetID != null) {
                    resultsetID.close();
                }
                if (preparedstatementID != null) {
                    preparedstatementID.close();
                }
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    /**
     *
     * @param memberName
     * @return
     */
    public int findID(String memberName) {

        try {
            String sql = "SELECT ID FROM [memberProfile" + Login.monthyear + "] WHERE firstname='" + memberName + "'";
            preparedstatementID = connRefID.prepareStatement(sql);
            resultsetID = preparedstatementID.executeQuery();
            resultsetID.next();
            int id = resultsetID.getInt("ID");

            return id;
        } catch (SQLException ex) {
            Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultsetID != null) {
                    resultsetID.close();
                }
                if (preparedstatementID != null) {
                    preparedstatementID.close();
                }
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public int topIDinList() {
        try {
            //projecting table in profile report
            String sql = "SELECT MIN(ID)AS min FROM [memberProfile" + Login.monthyear + "]";
            preparedstatementID = connRefID.prepareStatement(sql);
            resultsetID = preparedstatementID.executeQuery();
            int topid = resultsetID.getInt("min");

            return topid;

        } catch (SQLException ex) {
            Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultsetID != null) {
                    resultsetID.close();
                }
                if (preparedstatementID != null) {
                    preparedstatementID.close();
                }
            } catch (SQLException e) {
            }
        }
        return 0;
    }
}
