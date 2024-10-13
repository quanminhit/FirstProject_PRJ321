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
public class LoginServlet extends HttpServlet {

//    private final String INVALID_PAGE = "invalidPage";
//    private final String SEARCH_PAGE = "homePage";
//    private final String LOGIN_PAGE = "login.jsp";

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
        //B1: get all client info
        //Không gõ từng ký tự trong parameter, copy đi
        String username = request.getParameter("txtusername");
        String password = request.getParameter("txtpassword");
        String url = siteMaps.getProperty(ApplicationConstants.LoginFeature.INVALID_PAGE);
        try {
            //B2: Call Model //Muốn call 1 phương thức phải có instance của nó
            //2.1: New DAO object
            LoginnDAO dao = new LoginnDAO();
            //2.2: Call method of/Gọi DAO
//                boolean result = dao.checkLogin(username, password); // 10 va DAO tra ve cho controller
            loginnDTO result = dao.checkLogin(username, password); // 10 va DAO tra ve cho controller
            //B3: process result
            if (result != null) {
                url = siteMaps.getProperty(ApplicationConstants.LoginFeature.SEARCH_PAGE); // 13 va 14 va 15
                //ở lần đầu tiên login bắt buộc phải tạo session để lưu giá trị
                result.setPassword(password);
                HttpSession session = request.getSession();
                session.setAttribute("USERINFO", result);
            }
            //write cookies
//                    Cookie cookies = new Cookie(username, password);
//                    cookies.setMaxAge(60*3);
//                    response.addCookie(cookies);
//            } else {
//                errors.setWrongUserNameOrPassword("Wrong Username or Password");
//                request.setAttribute("LOGIN_ERROR", errors);
//            }
            //Tat ca url khong duoc khai bao trong code  ma phai khai bao bang bien hang
            //}//end user clicked Login button
        } catch (NamingException ex) {
            log("LoginServlet_ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet_ SQL: " + ex.getMessage());
        } finally {
            response.sendRedirect(url); //16 17 18
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//            out.close();
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
