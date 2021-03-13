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
import longnpt.dtos.ExamTmpDTO;
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class QuestionExamDAO {
    private static final Logger LOGGER = Logger.getLogger(QuestionExamDAO.class);

    public static List<ExamQuestionDTO> getExamQuestion(int quantity, String subject) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ExamQuestionDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "SELECT TOP " + quantity + " idQuestion,question,answer,createDate,status,point\n"
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

    public static int getIdChoice() throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "SELECT idChoice\n"
                        + "  FROM tblTmpAnswer\n"
                        + " ORDER\n"
                        + "    BY idChoice \n"
                        + "	DESC\n"
                        + "	OFFSET 0 Row\n"
                        + "FETCH NEXT 1 ROW ONLY";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
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

    public static int getIdSubject() throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "SELECT idChoice\n"
                        + " FROM tblTmpAnswer\n"
                        + "  ORDER\n"
                        + "  BY idSubject \n"
                        + "	DESC\n"
                        + "  	OFFSET 0 Row\n"
                        + "  FETCH NEXT 1 ROW ONLY";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
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

    public static int findPaging(String idQuestion) throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select r from\n"
                        + "(select ROW_NUMBER()over (order by question) as r, idChoice,question,answer,choice,isCorrect, idQuestion\n"
                        + "from tblTmpAnswerDetail) as x\n"
                        + "where idQuestion = " + idQuestion + "";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
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

    public static String getAnswerCorrect(int idQuestion) throws SQLException {
        String result = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select answer\n"
                        + "from tblTmpAnswerDetail\n"
                        + "where idQuestion = '" + idQuestion + "'\n";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("answer");
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

    public static int getSumCorrect() throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select COUNT(*) as sum\n"
                        + "from tblTmpAnswerDetail\n"
                        + "where isCorrect = '1'\n";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("sum");
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

    public static void addExamQuestion(int idChoice, String question, String answer, String choice, String isCorrect, int idQuestion) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "insert tblTmpAnswerDetail(idChoice,question,answer,choice,isCorrect,idQuestion)\n"
                        + "values('" + idChoice + "','" + question + "','" + answer + "','','','" + idQuestion + "')";
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

    public static void addHistory(String id, int idSubject, String startTime, String endTime, int sumCorrect, float point) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "insert tblHistory(id,idSubject,startTime,endTime,numOfCorrect,totalPoint)\n"
                        + "values('" + id + "','" + idSubject + "','" + startTime + "','" + endTime + "','" + sumCorrect + "','" + point + "')";
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

    public static void resetTableAfterSubmit() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "TRUNCATE TABLE tblTmpAnswerDetail;\n"
                        + "TRUNCATE TABLE tblTmpAnswer;";
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

    public static void saveAnswer(int idQuestion, String answer) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int isCorrect = 0;
        String answerCorrect = getAnswerCorrect(idQuestion);
        if (answer.trim().equals(answerCorrect)) {
            isCorrect = 1;
        } else {
            isCorrect = 0;
        }
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update tblTmpAnswerDetail\n"
                        + "set choice = '" + answer + "', isCorrect = '" + isCorrect + "'\n"
                        + "where idQuestion = '" + idQuestion + "'";
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

    public static List<ExamTmpDTO> getQuestionToDo(int index) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ExamTmpDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select idChoice,question,answer,choice,isCorrect,idQuestion from\n"
                        + "(select ROW_NUMBER()over (order by question) as r, idChoice,question,answer,choice,isCorrect, idQuestion\n"
                        + "from tblTmpAnswerDetail) as x\n"
                        + "where r = " + index + "";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int idChoice = rs.getInt("idChoice");
                    String question = rs.getString("question");
                    String answer = rs.getString("answer");
                    String choice = rs.getString("choice");
                    boolean isCorrect = rs.getBoolean("isCorrect");
                    int idQuestion = rs.getInt("idQuestion");
                    list.add(new ExamTmpDTO(idChoice, question, answer, choice, isCorrect, idQuestion));
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
}
