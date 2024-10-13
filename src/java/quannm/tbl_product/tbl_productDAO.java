/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.tbl_product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import quannm.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class tbl_productDAO {

    private List<tbl_productDTO> testListProductDTO;

    public List<tbl_productDTO> getTestListProductDTO() {
        return testListProductDTO;
    }

    public void listProductDTO() throws
            SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.getConnection();
            if (conn != null) {
                String sql = "SELECT sku, name, description, unitPrice, "
                        + "quantity, status "
                        + "FROM tbl_Product";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    Double unitPrice = rs.getDouble("unitPrice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    tbl_productDTO dto = new tbl_productDTO(sku, name, description, unitPrice, quantity, status);
                    if (this.testListProductDTO == null) {
                        this.testListProductDTO = new ArrayList<>();
                    }
                    this.testListProductDTO.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (ptm != null) {
                ptm.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    List<tbl_productDTO> listProductDTO;

    public List<tbl_productDTO> getListProductDTO() {
        return listProductDTO;
    }

    public void listProduct() throws
            SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select sku, name, description, unitPrice, "
                        + "quantity, status "
                        + "FROM tbl_Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    Double unitPrice = rs.getDouble("unitPrice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    tbl_productDTO dto = new tbl_productDTO(sku, name,
                            description, unitPrice, quantity, status);
                    if (this.listProductDTO == null) {
                        this.listProductDTO = new ArrayList<>();
                    } else if (quantity > 0 && status) {
                        this.listProductDTO.add(dto);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean updateQuantityById(String id, int quantity)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int effectRow = 0;
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update tbl_Product "
                        + "SET quantity = ? "
                        + "WHERE sku = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, id);
                effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
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
        return result;
    }

    public tbl_productDTO searchProductById(String id)
            throws SQLException, NamingException {
        this.listProduct();
        for (tbl_productDTO dto : this.getListProductDTO()) {
            if (dto.getSku().equals(id)) {
                return dto;
            }
        }
        return null;
    }

    public tbl_productDTO searchById(String id)
            throws SQLException, NamingException {
        this.listProductDTO();
        for (tbl_productDTO dto : testListProductDTO) {
            if (dto.getSku().equals(id)) {
                return dto;
            }
        }
        return null;
    }

}
