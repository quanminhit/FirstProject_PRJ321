/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quannm.cart.CartTest;
import quannm.cart.cartCreateError;
import quannm.tbl_product.tbl_productDAO;
import quannm.tbl_product.tbl_productDTO;
import quannm.util.ApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "TestAddToCartServlet", urlPatterns = {"/TestAddToCartServlet"})
public class TestAddToCartServlet extends HttpServlet {

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
        Properties siteMap = (Properties) context.getAttribute("SITEMAPS");
        cartCreateError errors = new cartCreateError();
        String url = siteMap.getProperty(ApplicationConstants.TestAddToCartFeature.ERROR_PAGE);
        String id = request.getParameter("chkProduct");
        try {
            tbl_productDAO dao = new tbl_productDAO();
            tbl_productDTO dto = dao.searchById(id);
            boolean foundError = false;
            if (dto != null) {
                int quantity = dto.getQuantity();
                if (quantity <= 0) {
                    foundError = true;
                    errors.setQuantityError("Product is out of stock, choose another one!");
                }
                boolean status = dto.isStatus();
                if (status == false) {
                    foundError = true;
                    errors.setStatusError("Currently product is in unavailable state, choose another one!");
                }
                if (foundError) {
                    request.setAttribute("ERROR_CART", errors);
                    url = siteMap.getProperty(ApplicationConstants.TestAddToCartFeature.TEST_SHOPPING);
                } else {
                    HttpSession session = request.getSession();
                    CartTest cart = (CartTest) session.getAttribute("CART_TEST");
                    if (cart == null) {
                        cart = new CartTest();
                    }
                    boolean result = false;
                    result = cart.addToCart(dto);
                    if (result) {
                        session.setAttribute("CART_TEST", cart);
                        url = siteMap.getProperty(ApplicationConstants.TestAddToCartFeature.TEST_SHOPPING);
                    }
                }
            }
        } catch (SQLException ex) {
            log("TestAddToCartServlet_ SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            log("TestAddToCartServlet_ NamingException " + ex.getMessage());
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
