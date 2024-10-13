/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quannm.loginn.LoginnDAO;
import quannm.loginn.loginnDTO;
import quannm.util.ApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "StartupServlet", urlPatterns = {"/StartupServlet"})
public class StartupServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String SEARCH_PAGE = "search.jsp";

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
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(ApplicationConstants.StartUpFeature.LOGIN_PAGE);
        try {
            HttpSession session = request.getSession();
            loginnDTO dto = (loginnDTO)session.getAttribute("USERINFO");
             //1. check existed cookies
            if (dto != null) {
                //2. get name and value (username and password)
                String username = dto.getUsername();
                String password = dto.getPassword();
                //3. checkLogin (call DAO/MODEL)
                LoginnDAO dao = new LoginnDAO();
                loginnDTO result = dao.checkLogin(username, password); // 10 va DAO tra ve cho controller
                //4. process result
                if (result != null) {
                    url = siteMaps.getProperty(ApplicationConstants.StartUpFeature.SEARCH_PAGE);
                }
            } //no first time

        } catch (SQLException ex) {
            log("Startup_ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Startup_ Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            //dùng gì cũng được vì khi response trả về Cookies đã lưu username và password rồi
            //Không cần lưu data
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
