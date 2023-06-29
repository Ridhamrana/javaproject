package com.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.CustomerModel.Customer;
import com.Sellermodel.Product;
import com.util.DBConnection;

public class CustomerDao {
	public static void InsertData (Customer c) {
		try {
			Connection con = DBConnection.createConnection();
			String sql = "insert into customer(Image,FirstName,LastName,MobileNumber,Email,Password) values(?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, c.getImage());
			pst.setString(2, c.getFirstName());
			pst.setString(3, c.getLastName());
			pst.setString(4, c.getMobileNumber());
			pst.setString(5, c.getEmail());
			pst.setString(6, c.getPassword());
			pst.executeUpdate();
			System.out.println("Data Inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Customer CheckLogin(Customer c) {
		Customer c1=null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql="Select * from customer where Email=? and Password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getEmail());
			pst.setString(2, c.getPassword());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				c1= new Customer();
				c1.setId(rs.getInt(1));
				c1.setImage(rs.getString("Image"));
				c1.setFirstName(rs.getString("FirstName"));
				c1.setLastName(rs.getString("LastName"));
				c1.setMobileNumber(rs.getString("MobileNumber"));
				c1.setEmail(rs.getString("Email"));
				c1.setPassword(rs.getString("Password"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
	}
	public static Customer getCustomerById(int id) {
		Customer c1 = null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql="select * from customer where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				c1 = new Customer();
				c1.setId(rs.getInt("id"));
				c1.setImage(rs.getString("Image"));
				c1.setFirstName(rs.getString("FirstName"));
				c1.setLastName(rs.getString("LastName"));
				c1.setMobileNumber(rs.getString("MobileNumber"));
				c1.setEmail(rs.getString("Email"));
				c1.setPassword(rs.getString("Password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
}
	public static List<Product> getallProducts(){
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			Connection conn = DBConnection.createConnection();
			String sql="select * from product";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Product p = new Product();
				p.setPid(rs.getInt("Pid"));
				p.setImage(rs.getString("Image"));
				p.setName(rs.getString("Name"));
				p.setMRP(rs.getString("MRP"));
				p.setServices(rs.getString("Services"));
				p.setDetails(rs.getString("Details"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
