/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.t_Order;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author ADMIN
 */
public class t_OrderDTO implements Serializable{
    private String id;
    private LocalDateTime date;
    private String customer;
    private String address;
    private double total;

    public t_OrderDTO() {
    }

    public t_OrderDTO(String id, LocalDateTime date, String customer, String address, double total) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.address = address;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
