package com.ensta.librarymanager.test;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

public class ServiceTest {
	public static void main(String[] args) {
		
		TestEmpruntService();
		TestLivreService();
		TestMembreService();
		
	}
	
	public static void TestLivreService() {
		LivreService livreService = LivreService.getInstance();

		try {
			System.out.println(livreService.count());
			System.out.println(livreService.getListDispo());
			System.out.println(livreService.getList());
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public static void TestMembreService() {
		MembreService membreService = MembreService.getInstance();

		try {
			System.out.println(membreService.count());
			System.out.println(membreService.getListMembreEmpruntPossible());
			System.out.println(membreService.getList());
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public static void TestEmpruntService() {
		EmpruntService empruntService = EmpruntService.getInstance();

		try {
			System.out.println(empruntService.count());
			System.out.println(empruntService.getListCurrent());			
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	

}
