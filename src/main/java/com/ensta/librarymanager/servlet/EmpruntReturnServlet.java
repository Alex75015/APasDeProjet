package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreService;

@WebServlet("/emprunt_return")
public class EmpruntReturnServlet extends HttpServlet{
	LivreService livreService = LivreService.getInstance();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			request.setAttribute("livreCount", this.livreService.count());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp")
			.forward(request, response);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//try {
			//int count = request.getAttribute("livreCount"); // exemple
			String nom2 = request.getParameter("nom"); // récupérer ce qu'il y a dans URL
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/View/dashboard.jsp")
			.forward(request, response);
			
		//} catch (ServiceException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		//this.doGet(request, response);
	}

}

