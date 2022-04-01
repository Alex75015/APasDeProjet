package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/emprunt_list")
public class EmpruntListServlet extends HttpServlet{
	EmpruntService empruntService = EmpruntService.getInstance();
	MembreService membreService = MembreService.getInstance();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			request.setAttribute("empruntCurrentList", this.empruntService.getListCurrent());
			request.setAttribute("membreList", this.membreService.getList());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp")
			.forward(request, response);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
