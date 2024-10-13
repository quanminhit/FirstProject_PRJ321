/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.loginn;
import java.io.Serializable;
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
public class LoginnDAO implements Serializable {

    //DAO giup truy cap phuong thuc de truy cap DB
    //Helmer class/Helmer method chua nhung thang xai chung
    public loginnDTO checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException,*/ NamingException {
        //noi dung cua buoc 11/ GOM 5 buoc
        //Khi doi tuong la OBJECT va lien quan den JDPC buoc 1: Khai báo, 2. xử lý, 3. đóng lại
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        loginnDTO result = null;
        try {
            //1. get Connection
            con = DBHelper.getConnection(); // buoc 11 trong so do ket noi db
            //Sau khi open connection phai kiem tra ket noi thanh cong chua
            //check connection co ton tai chua
            if (con != null) {
                //2. Create SQL String
                String sql = "Select lastName, isAdmin "
                        + "From userLogin "
                        + "Where username = ? "
                        + "And password = ?";
                // dau ? trong sql la vi tri truyen tham so tu trai sang phai, bat dau tu so 1
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                //set parameters
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                //Cau lenh SQL tren la Select => tra ra Result set
                //Result set: tu statement excute query
                //khi statement khong truyen gi ca
                //statement nap ben cho excute
                //prepaired statement da nap o tren roi
                rs = stm.executeQuery();
                //5. Process Result
                //neu tra ra 1 dong dung if/ nhieu dong tra ve while
                //tra ve 1 vi username va password la khoa chinh (primary key) 
                if (rs.next()) {
                    //map ---> get data from result, set data to DTO's property
                    String fullName = rs.getString("lastName");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    result = new loginnDTO(username, null, fullName, isAdmin);
                } //end username and password are verified
            }//end connection has been available
        } finally {
            //thang nao khai bao sau thi dong truoc
            //No le thuoc vao nhau
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
        return result; // ket thuc buoc 12
    }

    private List<loginnDTO> accounts;

    public List<loginnDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws
            /*ClassNotFoundException,*/ SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. get Connection
            con = DBHelper.getConnection(); // buoc 11 trong so do ket noi db
            //Sau khi open connection phai kiem tra ket noi thanh cong chua
            //check connection co ton tai chua
            if (con != null) {
                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From UserLogin "
                        + "Where lastname Like ?";
                // dau ? trong sql la vi tri truyen tham so tu trai sang phai, bat dau tu so 1
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result             
                while (rs.next()) {
                    //5.1 get data from result set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO properties
                    loginnDTO dto = new loginnDTO(
                            username, password, fullname, role);
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    } //accounts have not EXISTED
                    this.accounts.add(dto);
                }//end account has existed
            }//end connection has been available
        } finally {
            //thang nao khai bao sau thi dong truoc
            //No le thuoc vao nhau
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

    public boolean deleteAccounts(String username)
            throws SQLException, /*ClassNotFoundException,*/ NamingException {
        //noi dung cua buoc 11/ GOM 5 buoc
        //Khi doi tuong la OBJECT va lien quan den JDPC buoc 1: Khai báo, 2. xử lý, 3. đóng lại
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get Connection
            con = DBHelper.getConnection(); // buoc 11 trong so do ket noi db
            //Sau khi open connection phai kiem tra ket noi thanh cong chua
            //check connection co ton tai chua
            if (con != null) {
                //2. Create SQL String
                String sql = "Delete From UserLogin "
                        + "Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                //set parameters
                stm.setString(1, username);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    result = true;
                }
                //end username and password are verified
            }//end connection has been available
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
        return result; // ket thuc buoc 12
    }

    public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            System.out.println(username);
            System.out.println(password);
            System.out.println(isAdmin);
            con = DBHelper.getConnection();
            if (con != null) {
                System.out.println("K");
                String sql = "Update userLogin "
                        + "SET password = ?"
                        + ", isAdmin = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
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

    public boolean createAccount(loginnDTO account)
            throws SQLException, /*ClassNotFoundException,*/ NamingException {
        //noi dung cua buoc 11/ GOM 5 buoc
        //Khi doi tuong la OBJECT va lien quan den JDPC buoc 1: Khai báo, 2. xử lý, 3. đóng lại
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get Connection
            con = DBHelper.getConnection(); // buoc 11 trong so do ket noi db
            //Sau khi open connection phai kiem tra ket noi thanh cong chua
            //check connection co ton tai chua
            if (con != null) {
                //2. Create SQL String
                String sql = "Insert Into UserLogin("
                        + "username, password, lastname, isAdmin"
                        + ") Values ("
                        + "?, ?, ?, ?"
                        + ") ";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                //set parameters
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    result = true;
                }
                //end username and password are verified
            }//end connection has been available
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
        return result; // ket thuc buoc 12
    }
}
