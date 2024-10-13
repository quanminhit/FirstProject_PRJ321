/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.t_OrderDetail;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class t_OrderDetailDTO implements Serializable {

    private String productId;
    private double unitPrice;
    private int quantity;
    private double total;
    private String orderId;

    public t_OrderDetailDTO() {
    }

    public t_OrderDetailDTO(String productId, double unitPrice, int quantity, double total, String orderId) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
