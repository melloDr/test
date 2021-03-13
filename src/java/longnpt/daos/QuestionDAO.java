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
import longnpt.dtos.ExamQuestionDTO;
import longnpt.dtos.QuestionDTO;
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class QuestionDAO {
        private static final Logger LOGGER = Logger.getLogger(QuestionDAO.class);

    public static void addQuestion(String question, String answer, String createDate, String subjectId, String status) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "insert tblQuestion(question,answer,createDate,subjectId,status)\n"
                        + "values('" + question + "','" + answer + "','" + createDate + "','" + subjectId + "','" + status + "')";
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

    public static void deleteQuestion(String id) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update tblQuestion\n"
                        + "set status = 'false'\n"
                        + "where idQuestion= '" + id + "'";
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

    public static void updateQuestion(String id, String answer, String subject, String status) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update tblQuestion\n"
                        + "set answer = '" + answer + "', idSubject = '" + subject + "', status = '" + status + "'\n"
                        + "where idQuestion = '" + id + "'";
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

    public static List<QuestionDTO> getAllQuestion(int index) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<QuestionDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select question, answer, createDate, name, status, idQuestion from\n"
                        + "(select ROW_NUMBER() over (order by name) as r,question,answer,createDate, s.name as name,status,idQuestion\n"
                        + "from tblQuestion q join tblSubject s\n"
                        + "on q.idSubject = s.idSubject\n"
                        + ") as x\n"
                        + "where r between " + index + " * 20 - 19 and " + index + " * 20";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String question = rs.getString("question");
                    String answer = rs.getString("answer");
                    String createDate = rs.getString("createDate");
                    String subjectId = rs.getString("name");
                    String status = rs.getString("status");
                    int idQuestion = rs.getInt("idQuestion");
                    list.add(new QuestionDTO(question, answer, createDate, subjectId, status, idQuestion));
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
    
    
//    public static String[] getTmpQuestion(int index) throws SQLException {
//        Connection cn = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        List<QuestionDTO> list = new ArrayList<>();
//        try {
//            cn = DBHelper.getConnection();
//            if (cn != null) {
//                String sql = "select question, answer, createDate, name, status, idQuestion from\n"
//                        + "(select ROW_NUMBER() over (order by name) as r,question,answer,createDate, s.name as name,status,idQuestion\n"
//                        + "from tblQuestion q join tblSubject s\n"
//                        + "on q.idSubject = s.idSubject\n"
//                        + ") as x\n"
//                        + "where r between " + index + " * 20 - 19 and " + index + " * 20";
//                pst = cn.prepareStatement(sql);
//                rs = pst.executeQuery();
//                while (rs.next()) {
//                    String question = rs.getString("question");
//                    String answer = rs.getString("answer");
//                    String createDate = rs.getString("createDate");
//                    String subjectId = rs.getString("name");
//                    String status = rs.getString("status");
//                    int idQuestion = rs.getInt("idQuestion");
//                    list.add(new QuestionDTO(question, answer, createDate, subjectId, status, idQuestion));
//                }
//            }
//        } catch (Exception e) {
//        } finally {
//            if (pst != null) {
//                pst.close();
//            }
//            if (cn != null) {
//                cn.close();
//            }
//        }
//        return String[];
//    }

    public static List<ExamQuestionDTO> getExamQuestion(String subject) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ExamQuestionDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "SELECT TOP 50 idQuestion,question,answer,createDate,s.name,status,point\n"
                        + "FROM tblQuestion q join tblSubject s\n"
                        + "on s.idSubject = q.idSubject\n"
                        + "where s.name = '" + subject + "' and status = 'true'\n"
                        + "ORDER BY NEWID()";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String question = rs.getString("question");
                    String answer = rs.getString("answer");
                    String createDate = rs.getString("createDate");
                    String subjectId = rs.getString("name");
                    String status = rs.getString("status");
                    int idQuestion = rs.getInt("idQuestion");
                    list.add(new ExamQuestionDTO(idQuestion, question, answer, createDate, subject, status, idQuestion));
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

    public static int countTable() throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select COUNT(*) from tblQuestion where status = 'true'";
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

    

    public static void modifyContent(String id, String content) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update tblQuestion\n"
                        + "set question = '" + content + "'\n"
                        + "where idQuestion = '" + id + "'";
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

    public static void addExamVersion(String id, int idSubject) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "insert tblTmpAnswer(id,idSubject)\n"
                        + "values('" + id + "','" + idSubject + "')";
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

    public static String getContentQuestion(String id) throws SQLException {
        String result = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select question from tblQuestion where idQuestion = '" + id + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("question");
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

    public static List<QuestionDTO> searchQuestion(String searchBy, String content, int index) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<QuestionDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select question, answer, createDate, name, status, idQuestion from\n"
                        + "(select ROW_NUMBER() over (order by name) as r, question, answer, createDate, s.name, status, idQuestion\n"
                        + "from tblQuestion q join tblSubject s\n"
                        + "on s.idSubject = q.idSubject\n"
                        + "where " + searchBy + " like '%" + content + "%') as x\n"
                        + "where r  between " + index + " * 20-19 and  " + index + " *20";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String question = rs.getString("question");
                    String answer = rs.getString("answer");
                    String createDate = rs.getString("createDate");
                    String subjectId = rs.getString("name");
                    String status = rs.getString("status");
                    int idQuestion = rs.getInt("idQuestion");
                    list.add(new QuestionDTO(question, answer, createDate, subjectId, status, idQuestion));
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

    public static int countSearchQuestion(String searchBy, String content) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select COUNT(*)\n"
                        + "from tblQuestion q join tblSubject s\n"
                        + "on s.idSubject = q.idSubject\n"
                        + "where " + searchBy + " like '%" + content + "%'\n";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1);
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
        return result;
    }

}
