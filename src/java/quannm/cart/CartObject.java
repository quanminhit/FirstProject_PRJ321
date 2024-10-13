/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import quannm.tbl_product.tbl_productDAO;
import quannm.tbl_product.tbl_productDTO;

/**
 *
 * @author ADMIN
 */
//đặt khác vì nó không mapping vs DB
public class CartObject implements Serializable {
//thêm code nhe
    private Map<String, tbl_productDTO> items;
    //không tồn tại set vì bỏ từng món vào giỏ chứ không úp sọt khác vào giỏ

    public Map<String, tbl_productDTO> getItems() {
        return items; 
    }

    public boolean addItemToCart(String id) 
            throws SQLException, NamingException {
        boolean result = false;

        //Bỏ đồ vào ngăn chứa đồ
        //1. check valid id, quantity, status
        if (id == null) {
            return result;
        }
        //kiểm tra id không có khoảng trắng và rỗng
        if (id.trim().isEmpty()) {
            return result;
        }
        //2. check existed items
        //kiểm tra giỏ đồ không tồn tại để tạo mới
        //không tồn tại thì không làm gì cả
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. check existed item
        //kiểm tra món đồ có tồn tại để tăng số lượng
        int quantity = 1;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id).getQuantity() + 1;
        }
        //4. drops item to items
        tbl_productDAO dao = new tbl_productDAO();
        tbl_productDTO dto = dao.searchProductById(id);
        dto.setQuantity(quantity);
        this.items.put(id, dto);

        result = true;
        return result;
    }

    public boolean removeItemFromCart(String id) {
        boolean result = false;
        //1. check existed items
        //kiểm tra giỏ đồ có tồn tại, 
        if (this.items != null) {
            //2. check existed item 
            //kiểm tra món đồ có tồn tại thì mới xóa
            if (this.items.containsKey(id)) {
                //3. nếu tồn tại thì remove from items
                this.items.remove(id);
                //phai check size khi xoa xong 1 phan tu thi check xem 
                //con phan tu nao ton tai khong --> neu khong xoa luon
                if (this.items.isEmpty()) {
                    this.items = null;
                }
                result = true;
            }//item has existed
        }//items have existed
        //check Map khác null 
        return result;
    }
}
