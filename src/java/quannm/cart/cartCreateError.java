/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.cart;

/**
 *
 * @author ADMIN
 */
public class cartCreateError {

    protected String statusError;
    protected String quantityError;

    public cartCreateError() {
    }

    public cartCreateError(String statusError, String quantityError) {
        this.statusError = statusError;
        this.quantityError = quantityError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    
}
