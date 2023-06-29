package com.SellerController;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.SellerDao.SellerDao;
import com.Sellermodel.Seller;

@WebServlet("/SellerController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024 * 512)

public class SellerController extends HttpServlet {
	
	private String extractfilename(Part file) {
	    String cd = file.getHeader("content-disposition");
	    System.out.println(cd);
	    String[] items = cd.split(";");
	    for (String string : items) {
	        if (string.trim().startsWith("filename")) {
	            return string.substring(string.indexOf("=") + 2, string.length()-1);
	        }
	    }
	    return "";
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("Submit")) {
			String savePath = "D:\\Tops\\JAVA\\E-shopping\\src\\main\\webapp\\seller-img";   
			File fileSaveDir=new File(savePath);
	        if(!fileSaveDir.exists()){
	            fileSaveDir.mkdir();
	        }
	        Part file1 = request.getPart("Image");
		 	String fileName=extractfilename(file1);
		    file1.write(savePath + File.separator + fileName);
		    String filePath= savePath + File.separator + fileName ;
		    
		    String savePath2 = "D:\\Tops\\JAVA\\E-shopping\\src\\main\\webapp\\seller-img";
	        File imgSaveDir=new File(savePath2);
	        if(!imgSaveDir.exists()){
	            imgSaveDir.mkdir();
	        }


			Seller s = new Seller();
			s.setImage(fileName);
			s.setFirstName(request.getParameter("FirstName"));
			s.setLastName(request.getParameter("LastName"));
			s.setMobileNumber(request.getParameter("MobileNumber"));
			s.setEmail(request.getParameter("Email"));
			s.setPassword(request.getParameter("Password"));
			
			System.out.println(request.getParameter("FirstName"));
			System.out.println(request.getParameter("LastName"));
			System.out.println(request.getParameter("MobileNumber"));
			System.out.println(request.getParameter("Email"));
			System.out.println(request.getParameter("Password"));
			
			SellerDao.insertData(s);
			request.setAttribute("smsg1", "Registered Successfully");
			request.getRequestDispatcher("seller-login.jsp").forward(request, response);
	}
		
		if(action.equalsIgnoreCase("Login")) {
			Seller s = new Seller();
			s.setEmail(request.getParameter("Email"));
			s.setPassword(request.getParameter("Password"));
			
			String Email = request.getParameter("Email");
			String Password = request.getParameter("Password");
			
			Seller ss = SellerDao.CheckLogin(s);
			if(Email.equals(null) || Password.equals(null)) {
				request.setAttribute("smsg", "All fields are Mandatory");
				request.getRequestDispatcher("seller-login.jsp").forward(request, response);
			}
			else if(ss==null) {
				request.setAttribute("smsg", "Email or Password is Incorrect");
				request.getRequestDispatcher("seller-login.jsp").forward(request, response);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("s", ss);
				request.getRequestDispatcher("seller-index.jsp").forward(request, response);
			}
		}
}
}
