/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.t_OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import quannm.tbl_product.tbl_productDTO;
import quannm.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class t_OrderDetailDAO {

    public boolean addOrderDetailDAO(tbl_productDTO product, double total, String orderId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int effectRow = 0;
        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Insert into t_OrderDetail(productId, unitPrice, quantity, total, orderId) "
                        + "values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, product.getSku());
                stm.setDouble(2, product.getUnitPrice());
                stm.setInt(3, product.getQuantity());
                stm.setDouble(4, total);
                stm.setString(5, orderId);
                effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    check = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
}
