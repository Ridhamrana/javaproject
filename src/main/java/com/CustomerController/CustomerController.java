package com.CustomerController;

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

import com.CustomerDao.CustomerDao;
import com.CustomerModel.Customer;


@WebServlet("/CustomerController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024* 512)
public class CustomerController extends HttpServlet {
	
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
			String savePath = "D:\\Tops\\JAVA\\E-shopping\\src\\main\\webapp\\customer-img";   
			File fileSaveDir=new File(savePath);
	        if(!fileSaveDir.exists()){
	            fileSaveDir.mkdir();
	        }
	        Part file1 = request.getPart("Image");
		 	String fileName=extractfilename(file1);
		    file1.write(savePath + File.separator + fileName);
		    String filePath= savePath + File.separator + fileName ;
		    
		    String savePath2 = "D:\\Tops\\JAVA\\E-shopping\\src\\main\\webapp\\customer-img";
	        File imgSaveDir=new File(savePath2);
	        if(!imgSaveDir.exists()){
	            imgSaveDir.mkdir();
	        }
			Customer c = new Customer();
			c.setImage(fileName);
			c.setFirstName(request.getParameter("FirstName"));
			c.setLastName(request.getParameter("LastName"));
			c.setMobileNumber(request.getParameter("MobileNumber"));
			c.setEmail(request.getParameter("Email"));
			c.setPassword(request.getParameter("Password"));
			
			System.out.println(request.getParameter("FirstName"));
			System.out.println(request.getParameter("LastName"));
			System.out.println(request.getParameter("MobileNumber"));
			System.out.println(request.getParameter("Email"));
			System.out.println(request.getParameter("Password"));
			
			CustomerDao.InsertData(c);
			request.setAttribute("msg", "Registered Successfully");
			request.getRequestDispatcher("customer-login.jsp").forward(request, response);
		}
		if(action.equalsIgnoreCase("Login")) {
			Customer c = new Customer();
			c.setEmail(request.getParameter("Email"));
			c.setPassword(request.getParameter("Password"));
			
			String Email = request.getParameter("Email");
			String Password = request.getParameter("Password");
			
			Customer cc = CustomerDao.CheckLogin(c);
			if(Email.equals(null) || Password.equals(null)) {
				request.setAttribute("msg", "All fields are Mandatory");
				request.getRequestDispatcher("customer-login.jsp").forward(request, response);
			}
			else if(cc==null) {
				request.setAttribute("msg", "Email or Password is Incorrect");
				request.getRequestDispatcher("customer-login.jsp").forward(request, response);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("msg", cc);
				request.getRequestDispatcher("customer-index.jsp").forward(request, response);
			}
		}
	}

}
