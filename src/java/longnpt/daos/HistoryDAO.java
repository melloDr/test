/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import longnpt.dtos.HistoryDTO;
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class HistoryDAO {

    private static final Logger LOGGER = Logger.getLogger(HistoryDAO.class);

    public static List<HistoryDTO> getExamQuestion(String userName, int idSub) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<HistoryDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select idHistory,id,idSubject,startTime,endTime,numOfCorrect,totalPoint\n"
                        + "from tblHistory\n"
                        + "where idSubject = '" + idSub + "' and id = '" + userName + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int idHistory = rs.getInt("idHistory");
                    int idSubject = rs.getInt("idSubject");
                    String id = rs.getString("id");
                    String startTime = rs.getString("startTime");
                    String endTime = rs.getString("endTime");
                    int numOfCorrect = rs.getInt("numOfCorrect");
                    float totalPoint = rs.getFloat("totalPoint");
                    list.add(new HistoryDTO(idHistory, idSubject, id, startTime, endTime, numOfCorrect, totalPoint));
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public static int countHistory(String userName, int idSub) throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "	select COUNT(*)\n"
                        + "	from tblHistory\n"
                        + "	where idSubject = '" + idSub + "' and id = '" + userName + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return result;
    }
}
