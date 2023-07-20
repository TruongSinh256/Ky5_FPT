/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Color;
import models.CustomerOrder;
import models.Order;
import models.Product;
import models.ShopInfo;
import models.Size;
import models.Stock;

/**
 *
 * @author Asus
 */
public class AdminDAO {

    Connection conn = null;        // keets noois sql
    PreparedStatement ps = null;  // nems cau lenh query
    ResultSet rs = null;    // keet qua tra ve

    public int getTotalOfProduct() {
        String sql = "select COUNT(*) from Product";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getTotalOfOrder() {
        String sql = "select COUNT(*) from [Order Detail]";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getTotalOfRevenue() {
        String sql = "select SUM(o.totalMoney) from [Order] o where o.orStatusID = 4";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProID(rs.getInt("proID"));
                p.setProName(rs.getString("proName"));
                p.setProDetail(rs.getString("proDetail"));
                p.setProPrice(rs.getFloat("proPrice"));
                p.setCaID(rs.getInt("caID"));
                p.setProStatus(rs.getBoolean("proStatus"));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> get10NewProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "Select  top 10 * from Product order by proID DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProID(rs.getInt("proID"));
                p.setProName(rs.getString("proName"));
                p.setProDetail(rs.getString("proDetail"));
                p.setProPrice(rs.getFloat("proPrice"));
                p.setCaID(rs.getInt("caID"));
                p.setProStatus(rs.getBoolean("proStatus"));
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;

    }

    public List<Stock> get10NewStock() {
        List<Stock> list = new ArrayList<>();
        String query = "select top 10 * from Stock order by stockID DESC ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Stock(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<CustomerOrder> get10NewOrderByStatusID() {
        List<CustomerOrder> list = new ArrayList<>();
        String query = "SELECT TOP 10 * FROM [Order] WHERE orStatusID = 1 ORDER BY orDate DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CustomerOrder(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getFloat(4),rs.getInt(5),rs.getInt(6)));
            }
                
        } catch (Exception e) {
        }
        return list;
    }

    public List<Color> getAllColor() {
        List<Color> list = new ArrayList<>();
        String sql = "select * from Color order by proColorName";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Color c = new Color();
                c.setProColorID(rs.getInt("proColorID"));
                c.setProColorName(rs.getString("proColorName"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Size> getAllSize() {
        List<Size> list = new ArrayList<>();
        String sql = "select * from Size";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Size c = new Size();
                c.setSizeID(rs.getInt("sizeID"));
                c.setSizeName(rs.getString("sizeName"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ShopInfo getAllInfo() {
        String query = "select * from [Shop Information]";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new ShopInfo(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void UpdateInfo(String address, String facebook, String gmail, String phone, String name) {
        String query = "UPDATE [Shop Information] SET [Address] = ?, [Facebook] = ?, [Gmail] = ?, [Phone] = ? WHERE [Name] = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, address);
            ps.setString(2, facebook);
            ps.setString(3, gmail);
            ps.setString(4, phone);
            ps.setString(5, name);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        
        for (CustomerOrder arg : dao.get10NewOrderByStatusID()) {
            System.out.println(arg.getGmail());
            
        }
    }

    
}
