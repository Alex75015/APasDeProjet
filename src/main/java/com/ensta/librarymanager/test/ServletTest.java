package com.ensta.librarymanager.test;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;

public class ServletTest {
	public static void main(String[] args) {
		
		TestEmpruntAddServlet();
		
	}
	
	public static void TestEmpruntAddServlet() {
		EmpruntService empruntService = EmpruntService.getInstance();

		try {
			System.out.println(empruntService.count());
			System.out.println(empruntService.getListCurrent());			
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
