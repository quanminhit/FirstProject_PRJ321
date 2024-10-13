/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quannm.cart.CartObject;
import quannm.tbl_product.tbl_productDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

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
        try {
            //1. Cust goes to his/her cart place
            //thấy ở phía client nên phải check
            //check session có tồn tại
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Cust takes his/her cart
                //Lấy name attribute ở addItemServlet
                CartObject cart = (CartObject) session.getAttribute("CART");
                //Check có tồn tại thì mới xóa
                if (cart != null) {
                    //3. Cust gets items
                    Map<String, tbl_productDTO> items = cart.getItems();
                    if (items != null) {
                        //4. Cust removes item from items
                        //lấy toàn bộ items được chọn để xóa
                        String[] selectedItems = request.getParameterValues("chkItem");
                        //check nếu cust không chọn mà vẫn xóa
                        if (selectedItems != null) {
                            for (String item : selectedItems) {
                                cart.removeItemFromCart(item);    
                            }
                            //cập nhật lại attributte
                            session.setAttribute("CART", cart);
                        }
                    }
                }
            }
        } finally {
            //refresh --> call previous function again using URL rewriting technique
            String urlRewriting = "DispatcherServlet?btAction=Test Remove Selected Items";
            //dùng forward sẽ trùng btAction --> tạo ra mảng Parameter"btAction" 
            //--> không biết gọi chức năng nào để chạy
            response.sendRedirect(urlRewriting);    
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
