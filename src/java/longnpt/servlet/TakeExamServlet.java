/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.daos.QuestionDAO;
import longnpt.daos.QuestionExamDAO;
import longnpt.dtos.ExamQuestionDTO;
import longnpt.dtos.ExamTmpDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class TakeExamServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TakeExamServlet.class);

    private static final String SUCCESS = "showQuestion.jsp";
    private static final String ERROR = "invalid.html";

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
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            String flagRandom = (String) session.getAttribute("FLAGRANDOM");
            String txtIdHiddenToSave = request.getParameter("txtIdHiddenToSave");
            String startTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            session.setAttribute("STARTTIME", startTime);
            int quantity = 0;
            String sub = (String) session.getAttribute("SUBJECTTODO");
            String btnAction = request.getParameter("btnAction");
            if (sub == null) {
                session.setAttribute("SUBJECTTODO", btnAction);
            }
            int idSubject = 0;
            if ("Java Desktop".equals(btnAction)) {
                idSubject = 1;
                quantity = 40;
            }
            if ("Java Web".equals(btnAction)) {
                idSubject = 2;
                quantity = 50;
            }
            session.setAttribute("IDSUBJECT", idSubject);
            session.setAttribute("QUANTITY", quantity);
            String id = (String) session.getAttribute("LOGIN_ID");
            if (flagRandom != "0") {
                QuestionDAO.addExamVersion(id, idSubject);// thêm người thi
                //-------------------
                int idChoice = QuestionExamDAO.getIdChoice();
                List<ExamQuestionDTO> list = QuestionExamDAO.getExamQuestion(quantity, btnAction); //lấy được 1 arrayliist ra 
                for (ExamQuestionDTO l : list) {
                    QuestionExamDAO.addExamQuestion(idChoice, l.getQuestion(), l.getAnswer(), null, null, l.getIdQuestion());
                }// gán nó vào database bảng tmp chi tiết           
            }
            session.setAttribute("FLAGRANDOM", "1");//gán biến cờ
            //------------------ Load câu hỏi lên
            String btnPage = request.getParameter("txtPaging");
            int indexString = 1;
            if (btnPage == null) {
                if (txtIdHiddenToSave != null) {
                    indexString = QuestionExamDAO.findPaging(txtIdHiddenToSave) + 1;
                }
            }
            if (btnPage != null) {
                indexString = Integer.parseInt(btnPage);
            }
            List<ExamTmpDTO> listToDo = null;
            listToDo = QuestionExamDAO.getQuestionToDo(indexString);// đã mang được câu hỏi
            session.setAttribute("LISTTODO", listToDo);
            url = SUCCESS;
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
