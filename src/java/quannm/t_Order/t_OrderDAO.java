/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.t_Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.naming.NamingException;
import quannm.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class t_OrderDAO {

    public String test() throws
            SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String orderResult = null;
        try {
            conn = DBHelper.getConnection();
            if (conn != null) {
                String sql = "Select top 1 orderID "
                        + "FROM t_Order "
                        + "Order by orderId DESC";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    orderResult = rs.getString("orderID");
                    String subString = orderResult.substring(2);
                    int numebr = Integer.parseInt(subString);
                    if (numebr < 9) {
                        orderResult = "HD" + "00" + (numebr + 1);
                    } else if (numebr < 99) {
                        orderResult = "HD" + "0" + (numebr + 1);
                    } else {
                        orderResult = "HD" + (numebr + 1);
                    }
                } else {
                    return "HD000";
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orderResult;
    }

    public String createOrderId()
            throws SQLException, NamingException, NumberFormatException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String orderId = null;
        try {
            conn = DBHelper.getConnection();
            if (conn != null) {
                String sql = "Select top 1 orderId "
                        + "FROM t_Order "
                        + "Order by orderId DESC";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                String idString = "HD";
                if (rs.next()) {
                    orderId = rs.getString("orderId");
                } else {
                    return idString + "000";
                }
                if (orderId != null) {
                    String subID = orderId.substring(2);
                    int numberId = Integer.parseInt(subID);
                    if (numberId < 9) {
                        orderId = idString + "00" + (numberId + 1);
                    } else if (numberId < 99) {
                        orderId = idString + "0" + (numberId + 1);
                    } else {
                        orderId = idString + (numberId + 1);
                    }
                }
            }
        } finally {
            //thang nao khai bao sau thi dong truoc
            //No le thuoc vao nhau
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orderId;
    }

    public boolean addOrder(String idOrder, LocalDateTime date, String nameCust, String address, double totalOrder)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int effectRow = 0;
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Insert into t_Order(orderId,date,Customer,address,total) "
                        + "values (?,?,?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, idOrder);
                stm.setTimestamp(2, Timestamp.valueOf(date));
                stm.setString(3, nameCust);
                stm.setString(4, address);
                stm.setDouble(5, totalOrder);
                effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }

        } finally {
            //thang nao khai bao sau thi dong truoc
            //No le thuoc vao nhau
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
