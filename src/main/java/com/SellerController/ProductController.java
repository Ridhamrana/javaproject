package com.SellerController;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.SellerDao.ProductDao;
import com.Sellermodel.Product;

@WebServlet("/ProductController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024* 512)
public class ProductController extends HttpServlet {

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
			String savePath = "D:\\Tops\\JAVA\\E-shopping\\src\\main\\webapp\\product-img";   
			File fileSaveDir=new File(savePath);
	        if(!fileSaveDir.exists()){
	            fileSaveDir.mkdir();
	        }
	        Part file1 = request.getPart("Image");
		 	String fileName=extractfilename(file1);
		    file1.write(savePath + File.separator + fileName);
		    String filePath= savePath + File.separator + fileName ;
		    
		    String savePath2 = "D:\\Tops\\JAVA\\E-shopping\\src\\main\\webapp\\product-img";
	        File imgSaveDir=new File(savePath2);
	        if(!imgSaveDir.exists()){
	            imgSaveDir.mkdir();
	        }
	        Product p = new Product();
	        p.setSid(Integer.parseInt(request.getParameter("Sid")));
	        p.setImage(fileName);
	        p.setName(request.getParameter("Name"));
	        p.setMRP(request.getParameter("MRP"));
	        p.setServices(request.getParameter("Services"));
	        p.setDetails(request.getParameter("Details"));
	        ProductDao.insertData(p);
	        response.sendRedirect("seller-index.jsp");
		}
		if(action.equalsIgnoreCase("Edit")) {
			int id = Integer.parseInt(request.getParameter("Pid"));
			Product p =  ProductDao.getProductByID(id);
			request.setAttribute("msg", p);
			request.getRequestDispatcher("product-edit.jsp").forward(request, response);
		}
		if(action.equalsIgnoreCase("Update")) {
			String savePath = "D:\\Tops\\JAVA\\E-shopping\\src\\main\\webapp\\product-img";  
			File fileSaveDir=new File(savePath);
	        if(!fileSaveDir.exists()){
	            fileSaveDir.mkdir();
	        }
	        Part file1 = request.getPart("Image");
		 	String fileName=extractfilename(file1);
		    file1.write(savePath + File.separator + fileName);
		    String filePath= savePath + File.separator + fileName ;
		    
		    String savePath2 = "D:\\Tops\\JAVA\\E-shopping\\src\\main\\webapp\\product-img";
	        File imgSaveDir=new File(savePath2);
	        if(!imgSaveDir.exists()){
	            imgSaveDir.mkdir();
	        }
	        Product p = new Product();
	        int Pid = Integer.parseInt(request.getParameter("Pid"));
			p.setPid(Pid);
	        p.setSid(Integer.parseInt(request.getParameter("Sid")));
	        p.setImage(fileName);
	        p.setName(request.getParameter("Name"));
	        p.setMRP(request.getParameter("MRP"));
	        p.setServices(request.getParameter("Services"));
	        p.setDetails(request.getParameter("Details"));
	        ProductDao.UpdateProduct(p);
	        response.sendRedirect("seller-index.jsp");
		}
		if(action.equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ProductDao.DeleteProduct(id);
			response.sendRedirect("seller-index.jsp");
		}
	}
	

}
