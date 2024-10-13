/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quannm.util.ApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DispatcherServlet", urlPatterns = {"/DispatcherServlet"})
public class DispatcherServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "";
//    private final String LOGIN_CONTROLLER = "loginController";
//    private final String SEARCH_LASTNAME_CONTROLLER = "searchController";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
//    private final String STARTUP_CONTROLLER = "StartupServlet";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
//    private final String SHOPPING_CONTROLLER = "ShoppingServlet";
//    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
//    private final String VIEW_CART_PAGE = "viewCart.jsp";
//    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
//    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";
//    private final String LOGOUT_CONTROLLER = "LogoutServlet";

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
        //0. Get current context and get siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //1. which button did user click?
        String button = request.getParameter("btAction");
//        String url = LOGIN_PAGE;
        String url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_PAGE);
        //sau khi lấy được username và password
        //check login 
        //nếu đúng chuyển qua trang search
        //nếu sai trả về login
        try {
            if (button == null) { //first time or app starts up
                //transfer login page
                //Check cookies to determine which page is transfered
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.STARTUP_CONTROLLER);
            } else if (button.equals("Login")) { //user clicked login
//                url = LOGIN_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")) { //user click search
//                url = SEARCH_LASTNAME_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.SEARCH_LASTNAME_CONTROLLER);
                //xuat lệnh 
            } else if (button.equals("delete")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.DELETE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Update")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.UPDATE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Go to shopping")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.SHOPPING_CONTROLLER);
            } else if (button.equals("Add Product To Your Cart")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.ADD_ITEM_TO_CART_CONTROLLER);
            } else if (button.equals("View Your Cart")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.VIEW_CART_PAGE);
            } else if (button.equals("Remove Selected Items")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.REMOVE_ITEM_FROM_CART_CONTROLLER);
            } else if (button.equals("Create New Account")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.CREATE_NEW_ACCOUNT_CONTROLLER);
            } else if (button.equals("Logout")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGOUT_CONTROLLER);
            } else if (button.equals("CheckOut")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.CHECKOUT_CONTROLLER);
            } else if (button.equals("Go to test shopping")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.TEST_SHOPPING);
            } else if (button.equals("TestAddToCart")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.TEST_ADD_TO_CART_CONTROLLER);
            } else if (button.equals("TestViewCart")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.TEST_VIEWCART_PAGE);
            } else if (button.equals("Test Remove Selected Items")) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.TEST_REMOVE_ITEM_CONTROLLER);
            }
            
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
