package com.SellerDao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Sellermodel.Product;

import com.util.DBConnection;

public class ProductDao {
	public static void insertData(Product p) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql="insert into product(Sid,Image,Name,MRP,Services,Details) values(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, p.getSid());
			pst.setString(2, p.getImage());
			pst.setString(3, p.getName());
			pst.setString(4, p.getMRP());
			pst.setString(5, p.getServices());
			pst.setString(6, p.getDetails());
			pst.executeUpdate();
			System.out.println("data Uploaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	public static List<Product> getProductsByID(int id){
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			Connection conn = DBConnection.createConnection();
			String sql="select * from product where Sid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Product p = new Product();
				p.setPid(rs.getInt("Pid"));
				p.setSid(rs.getInt("Sid"));
				p.setImage(rs.getString("Image"));
				p.setName(rs.getString("Name"));
				p.setMRP(rs.getString("MRP"));;
				p.setServices(rs.getString("Services"));
				p.setDetails(rs.getString("Details"));
				list.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static Product getProductByID(int Pid) {
		Product p = null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql="select * from product where Pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Pid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				p = new Product();
				p.setPid(rs.getInt("Pid"));
				p.setSid(rs.getInt("Sid"));
				p.setImage(rs.getString("Image"));
				p.setName(rs.getString("Name"));
				p.setMRP(rs.getString("MRP"));;
				p.setServices(rs.getString("Services"));
				p.setDetails(rs.getString("Details"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	public static void UpdateProduct(Product p) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql="update product set Sid=?,Image=?,Name=?,MRP=?,Services=?,Details=? where Pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, p.getSid());
			pst.setString(2, p.getImage());
			pst.setString(3, p.getName());
			pst.setString(4, p.getMRP());
			pst.setString(5, p.getServices());
			pst.setString(6, p.getDetails());
			pst.setInt(7, p.getPid());
			pst.executeUpdate();
			System.out.println("Data Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void DeleteProduct(int Pid) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql="delete from product where Pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Pid);
			pst.executeUpdate();
			System.out.println("Data Deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
