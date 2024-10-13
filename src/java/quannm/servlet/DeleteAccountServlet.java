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
import quannm.loginn.LoginnDAO;
import quannm.util.ApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

//    private String ERROR_PAGE = "errors.html";

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
        //1.get all parameter
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(ApplicationConstants.DeleteFeature.ERROR_PAGE);
        try {
            //2. Call Model
            //2.1 New DAO object
            LoginnDAO dao = new LoginnDAO();
            //2.2 call methods of DAO
            boolean result = dao.deleteAccounts(username);
            //3. process result
            if (result) {
                // call previous functions again using URL Rewriting technique
                url = "DispatcherServlet?btAction=Search&txtSearchValue=" + searchValue;
            }
        } catch (SQLException ex) {
            log("DeleteAccountServlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteAccountServlet Naming: " + ex.getMessage());
        } finally {
            //forward khong xai duoc vi trung parameter btAction se tao ra mang 
            //---> xem lai noi dung trung parameter
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);          
            response.sendRedirect(url);
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
