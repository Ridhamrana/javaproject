package com.SellerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.CustomerModel.Customer;
import com.Sellermodel.Seller;
import com.util.DBConnection;

public class SellerDao {
	public static void insertData(Seller s) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "insert into seller(Image,FirstName,LastName,MobileNumber,Email,Password) values(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, s.getImage());
			pst.setString(2, s.getFirstName());
			pst.setString(3, s.getLastName());
			pst.setString(4, s.getMobileNumber());
			pst.setString(5, s.getEmail());
			pst.setString(6, s.getPassword());
			pst.executeUpdate();
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Seller CheckLogin(Seller s) {
		Seller s1 = null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "Select * from seller where Email=? and Password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, s.getEmail());
			pst.setString(2, s.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				s1 = new Seller();
				s1.setId(rs.getInt("id"));
				s1.setImage(rs.getString("Image"));
				s1.setFirstName(rs.getString("FirstName"));
				s1.setLastName(rs.getString("LastName"));
				s1.setMobileNumber(rs.getString("MobileNumber"));
				s1.setEmail(rs.getString("Email"));
				s1.setPassword(rs.getString("Password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s1;
	}

	public static Seller getSellerById(int id) {
		Seller s1 = null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "select * from seller where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				s1 = new Seller();
				s1.setId(rs.getInt("id"));
				s1.setImage(rs.getString("Image"));
				s1.setFirstName(rs.getString("FirstName"));
				s1.setLastName(rs.getString("LastName"));
				s1.setMobileNumber(rs.getString("MobileNumber"));
				s1.setEmail(rs.getString("Email"));
				s1.setPassword(rs.getString("Password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s1;
	}
}
