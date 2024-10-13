/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.cart;

import java.util.HashMap;
import quannm.tbl_product.tbl_productDTO;

/**
 *
 * @author ADMIN
 */
public class CartTest {

    private HashMap<String, tbl_productDTO> items;

    public HashMap<String, tbl_productDTO> getItems() {
        return items;
    }

    public boolean addToCart(tbl_productDTO dto) {
        boolean result = false;
        if (dto.getSku() == null) {
            return result;
        }
        if (dto.getSku().trim().isEmpty()) {
            return result;
        }
//        if (dto.getQuantity() <= 0) {
//            return result;
//        }
//        if (dto.isStatus() == false) {
//            return result;
//        }
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        int quantity = 1;
        if (this.items.containsKey(dto.getSku())) {
            quantity = this.items.get(dto.getSku()).getQuantity() + quantity;
        }
        dto.setQuantity(quantity);
        this.items.put(dto.getSku(), dto);

        result = true;
        return result;
    }
    
    public boolean removeItems(String id) {
        boolean result = false;
        if(this.items != null) {
            if(this.items.containsKey(id)) {
                this.items.remove(id);
            }
            if(this.items.isEmpty()) {
                this.items = null;
            }
            result = true;
        }
        return result;
    }
}
