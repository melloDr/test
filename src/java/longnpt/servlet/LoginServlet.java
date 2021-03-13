/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.daos.UserDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private static final String SUCCESSMEMBER = "takeExam.jsp";
    private static final String SUCCESSADMIN = "ShowQuestionServlet";
    private static final String ERROR = "login.jsp";
    private static final String ADMIN = "admin.jsp";

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
            String txtEmailLogin = request.getParameter("txtEmailLogin");
            String txtPasswordLogin = request.getParameter("txtPasswordLogin");
            String PasswordLogin = longnpt.utils.GFG.toHexString(longnpt.utils.GFG.getSHA(txtPasswordLogin));
            String name = UserDAO.checkLogin(txtEmailLogin, PasswordLogin);
            if (name != null || !name.trim().isEmpty()) {
                String role = UserDAO.getRole(name);
                session.setAttribute("LOGIN_ID", txtEmailLogin);
                session.setAttribute("LOGIN_NAME", name);
                if (role.trim().equals("member")) {
                    url = SUCCESSMEMBER;
                } else if (role.trim().equals("admin")) {
                    url = SUCCESSADMIN;
                }
            }
            if (name.trim().isEmpty()) {
                session.setAttribute("MSG_LOGIN", "Sorry but your account's not found!");
            }
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
