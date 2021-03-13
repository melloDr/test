/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.daos.QuestionExamDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class SubmitExamServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SubmitExamServlet.class);

    private static final String SUCCESS = "resultExam.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        HttpSession session = request.getSession();
        try {
            int idSubject = QuestionExamDAO.getIdSubject();
            int quantity = 0;
            if (idSubject == 1) {
                quantity = 40;
            } else if (idSubject == 2) {
                quantity = 50;
            }
            int sumCorrect = QuestionExamDAO.getSumCorrect();
            session.setAttribute("SUMCORRECT", sumCorrect);
            float point = (((float) sumCorrect) / quantity) * 10;
            session.setAttribute("POINT", point);
            String id = (String) session.getAttribute("LOGIN_ID");
            String startTime = (String) session.getAttribute("STARTTIME");
            String endTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            QuestionExamDAO.addHistory(id, idSubject, startTime, endTime, sumCorrect, point);
            QuestionExamDAO.resetTableAfterSubmit();
            session.setAttribute("FLAGRANDOM", "0");//nhả biến cờ
            session.setAttribute("FLAGTIMER", "no");
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
