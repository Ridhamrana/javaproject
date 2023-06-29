package com.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.CustomerModel.Cart;
import com.util.DBConnection;

public class CartDao {
	public static void InsertData (Cart c) {
		try {
			Connection con = DBConnection.createConnection();
			String sql = "insert into cart(Sid,Pid,Cid,Image,Name,Details,MRP) values(?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, c.getSid());
			pst.setInt(2, c.getPid());
			pst.setInt(3, c.getCid());
			pst.setString(4, c.getImage());
			pst.setString(5, c.getName());
			pst.setString(6, c.getDetails());
			pst.setString(7, c.getMRP());
			pst.executeUpdate();
			System.out.println("data Uploaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
