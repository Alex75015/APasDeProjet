package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet{
	EmpruntService empruntService = EmpruntService.getInstance();
	LivreService livreService = LivreService.getInstance();
	MembreService membreService = MembreService.getInstance();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			request.setAttribute("livreList", this.livreService.getList());
			request.setAttribute("membreList", this.membreService.getList());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp")
			.forward(request, response);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			//int count = request.getAttribute("livreCount"); // exemple
			int idLivre = Integer.valueOf(request.getParameter("idLivre")); // récupérer ce qu'il y a dans URL
			int idMembre = Integer.valueOf(request.getParameter("idMembre"));
			Emprunt emprunt = new Emprunt(idMembre, idLivre);
			
			//System.out.println(idLivre);
			//System.out.println(idMembre);
			//System.out.println(livreService.getById(idLivre));
			//System.out.println(membreService.getById(idMembre));
			empruntService.create(emprunt);
			
			//System.out.println(empruntService.count());
			//System.out.println(empruntService.getListCurrent());
			
			
			
			
			
			//this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp")
			//.forward(request, response);
			
			//throw new ServiceException("Le titre est vide !");
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		this.doGet(request, response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp")
		.forward(request, response);
	}

}

