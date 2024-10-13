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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quannm.loginn.LoginCreateError;
import quannm.loginn.LoginnDAO;
import quannm.loginn.loginnDTO;
import quannm.util.ApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
//
//    private final String ERROR_PAGE = "createAccount.jsp";
//    private final String LOGIN_PAGE = "login.html";

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
        //1. get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullName");
        String confirmPassword = request.getParameter("txtConfirm");
        boolean foundError = false;
        LoginCreateError errors = new LoginCreateError();
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(ApplicationConstants.CreateNewAccountFeature.CREATEACCOUNT_PAGE);
        //el và standard Action chỉ truy cập được class java bean
        try {
            //2. check user's errors
            //password dung moi bat confirmPassword
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundError = true;
                errors.setUsernameLengthError("Username is required from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password is required from 6 to 20 characters");
            }
            if (!confirmPassword.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatched("Confirm must match Password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                foundError = true;
                errors.setFullNameLengthError("Full name is required from 2 to 50 characters");
            }
            if (foundError) {
                //catching to specific attribute then go to error page to show
                //dung request scope chi muon show chu kh luu
                request.setAttribute("CREATE_ERROR", errors);
            } else { //no errors
                //3. call Model - DAO (insert to DB)
                LoginnDAO dao = new LoginnDAO();
                loginnDTO dto = new loginnDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = siteMaps.getProperty(ApplicationConstants.CreateNewAccountFeature.LOGIN_PAGE);
                }//create account is success
            }
            //4. process result
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet_ SQL: " + ex.getMessage());
            //if xử lý nhiều hơn 1 message
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                //error la 1 attribute ===> set Attribute
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet_ Naming: " + ex.getMessage());
        } finally {
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
