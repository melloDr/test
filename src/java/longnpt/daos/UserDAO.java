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
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    public static String checkLogin(String id, String pass) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String name = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select name\n"
                        + "from tblUser\n"
                        + "where id = '" + id + "' and password = '" + pass + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    name = rs.getString("name");
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
        return name;
    }

    public static String getRole(String name) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String role = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select role\n"
                        + "from tblUser\n"
                        + "where name = '" + name + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    role = rs.getString("role");
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
        return role;
    }

    public static void addUser(String id, String name, String pass, String role, String status) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "insert tblUser(id,name,password,role,status)\n"
                        + "values('" + id + "','" + name + "','" + pass + "','" + role + "','" + status + "')";
                pst = cn.prepareStatement(sql);
                pst.executeQuery();
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
    }
}
