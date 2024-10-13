/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quannm.loginn.LoginnDAO;
import quannm.loginn.loginnDTO;
import quannm.util.ApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "search.html";
//    private final String RESULT_PAGE = "search.jsp";

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

        //1. Get all user information
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String searchValue = request.getParameter("txtSearchValue");
        String url = siteMaps.getProperty(ApplicationConstants.SearchFeature.SEARCH_STATIC_PAGE);
        try {
            if (!searchValue.trim().isEmpty())  {
                //2. Call DAO
                //2.1 New DAO Object
                LoginnDAO dao = new LoginnDAO();
                //2.2 Call method of DAO 
                dao.searchLastname(searchValue);
                List<loginnDTO> result = dao.getAccounts();
                //3. process Result
                url = siteMaps.getProperty(ApplicationConstants.SearchFeature.SEARCH_PAGE);
                //đặt giá trị vào trong request SCope để đưa kết quả cho thằng view xử lí
                request.setAttribute("SEARCHR_RESULT", result);
                //user nhập gì thì hiện cái đó
            } // end user types valid
        } catch (SQLException ex) {
            log("SearchLastnameServlet SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchLastnameServlet Naming: " + ex.getMessage());
        } finally {
            //send directory response no se tra ve va từ đó toan bộ data sẽ bị hủy
            //send to View(có lưu trữ thông tin => request scope)
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
