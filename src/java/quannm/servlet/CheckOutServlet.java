/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quannm.cart.CartObject;
import quannm.t_Order.t_OrderCreateError;
import quannm.t_Order.t_OrderDAO;
import quannm.t_Order.t_OrderDTO;
import quannm.t_OrderDetail.t_OrderDetailDAO;
import quannm.tbl_product.tbl_productDAO;
import quannm.tbl_product.tbl_productDTO;
import quannm.util.ApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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
        String url = siteMaps.getProperty(ApplicationConstants.CheckOutFeature.VIEW_CART_PAGE);
        String nameCust = request.getParameter("txtName");
        String Address = request.getParameter("txtAddress");
        String total = request.getParameter("txtTotal");
        boolean foundError = false;
        t_OrderCreateError errors = new t_OrderCreateError();
        try {
            if (nameCust.trim().length() < 2 || nameCust.trim().length() > 50) {
                foundError = true;
                errors.setNameCustomerLengthError("Name is required from 2 to 50 characters");
            }
            if (Address.trim().length() < 5 || Address.trim().length() > 250) {
                foundError = true;
                errors.setAddressLengthError("Address is required from 10 to 250 characters");
            }
            if (foundError) {
                request.setAttribute("ERROR_ORDER", errors);
            } else {
                //Parse total Order parameter 
                double totalOrder = Double.parseDouble(total);
                boolean check = false;
                HttpSession session = request.getSession(false);
                CartObject cart = (CartObject) session.getAttribute("CART");
                //Check cart exist
                if (cart != null) {
                    t_OrderDAO daoOrder = new t_OrderDAO();
                    String idOrder = daoOrder.createOrderId();
                    LocalDateTime date = LocalDateTime.now();
                    check = daoOrder.addOrder(idOrder, date, nameCust, Address, totalOrder);
                    //Insert Order Detail if Insert Order successfully
                    if (check) {
                        t_OrderDetailDAO daoOrderDetail = new t_OrderDetailDAO();
                        tbl_productDAO daoProduct = new tbl_productDAO();
                        t_OrderDTO dtoOrder = new t_OrderDTO(idOrder, date, nameCust, Address, totalOrder);
                        //
                        for (tbl_productDTO dtoNewProduct : cart.getItems().values()) {
                            //Set total OrderDetails
                            double totalOrderDetails = dtoNewProduct.getQuantity() * dtoNewProduct.getUnitPrice();
                            //Add order Detail
                            daoOrderDetail.addOrderDetailDAO(dtoNewProduct, totalOrderDetails, idOrder);
                            //Update Quantity in stock of products after check out
                            //1. Find old quantity product
                            tbl_productDTO dtoOldProduct = daoProduct.searchProductById(dtoNewProduct.getSku());
                            int oldQuantity = dtoOldProduct.getQuantity();
                            //2. Set new quantity product
                            int newQuantity = oldQuantity - dtoNewProduct.getQuantity();
                            daoProduct.updateQuantityById(dtoNewProduct.getSku(), newQuantity);
                        }

                        request.setAttribute("CART_SHOW", cart);
                        request.setAttribute("ORDER", dtoOrder);
                        request.setAttribute("CHECKOUT_MESSAGE", "CheckOut Successfully!!");
                        //Remove Cart after Check Out
                        session.removeAttribute("CART");
                    }
                }
            }
        } catch (SQLException ex) {
            log("CheckOutServlet_ SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutServlet_ NamingException " + ex.getMessage());
        } catch (NumberFormatException ex) {
            log("CheckOutServlet_ NumberFormatException " + ex.getMessage());
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
