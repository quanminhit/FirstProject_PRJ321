/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.t_Order;

/**
 *
 * @author ADMIN
 */
public class t_OrderCreateError {
    protected String nameCustomerLengthError;
    protected String addressLengthError;

    public t_OrderCreateError() {
    }

    public t_OrderCreateError(String nameCustomerLengthError, String addressLengthError) {
        this.nameCustomerLengthError = nameCustomerLengthError;
        this.addressLengthError = addressLengthError;
    }

    public String getNameCustomerLengthError() {
        return nameCustomerLengthError;
    }

    public void setNameCustomerLengthError(String nameCustomerLengthError) {
        this.nameCustomerLengthError = nameCustomerLengthError;
    }

    public String getAddressLengthError() {
        return addressLengthError;
    }

    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }
    
}
