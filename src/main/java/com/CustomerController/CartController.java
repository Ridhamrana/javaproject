package com.CustomerController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.CustomerDao.CartDao;
import com.CustomerDao.CustomerDao;
import com.CustomerModel.Cart;
import com.CustomerModel.Customer;
import com.SellerDao.ProductDao;
import com.SellerDao.SellerDao;
import com.Sellermodel.Product;
import com.Sellermodel.Seller;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024* 512)
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("Submit")) {
			Product p = ProductDao.getProductByID(4);
			Seller s = SellerDao.getSellerById(1);
			Customer c1 = CustomerDao.getCustomerById(1);
			Cart c = new Cart();
			c.setSid(s.getId());
			c.setPid(p.getPid());
			c.setCid(c1.getId());
			c.setImage(p.getImage());
			c.setName(p.getName());
			c.setDetails(p.getDetails());
			c.setMRP(p.getMRP());
			
			System.out.println(request.getParameter("Sid"));
			System.out.println(request.getParameter("Pid"));
			System.out.println(request.getParameter("Cid"));
			System.out.println(request.getParameter("Name"));
			System.out.println(request.getParameter("Details"));
			System.out.println(request.getParameter("MRP"));
			
			CartDao.InsertData(c);
			request.setAttribute("msg", "Added To Cart");
			request.getRequestDispatcher("customer-index.jsp").forward(request, response);
		}
	}

}
