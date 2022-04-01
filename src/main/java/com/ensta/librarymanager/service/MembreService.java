package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class MembreService implements IMembreService {

	private MembreDao membreDao = MembreDao.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();

	////////////////////////
	// SINGLETON LIVREDAO DEBUT
	static MembreService instance; // pour implémenter singleton cf cours

	private MembreService() { // on met le constructeur private : pas possible d'init sans passer par
								// getInstance

	}

	public static MembreService getInstance() {
		if (instance == null) {
			instance = new MembreService();
		}
		return instance;
	}
	// SINGLETON FIN
	///////////////////
	
	@Override
	public List<Membre> getList() throws ServiceException {
		try {

			return membreDao.getList();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		
		try {
			List<Membre> membres = getList();
			List<Membre> membresDispo = new ArrayList<>();
			for(int i=0; i < membres.size(); i++) {
				if(empruntService.isEmpruntPossible(membres.get(i))) {
					membresDispo.add(membres.get(i));
				}
			}
			return membres;

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		try {

			return membreDao.getById(id);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public int create(Membre membre)
			throws ServiceException {
		
		try {
			if (membre.getNom() == "") {
				throw new ServiceException("Le titre est vide !");
			}

			membreDao.create(membre);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
		return 1;
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		try {
			if (membre.getNom() == "") {
				throw new ServiceException("Le titre est vide !");
			}

			membreDao.update(membre);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {

			membreDao.delete(id);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public int count() throws ServiceException {
		try {

			return membreDao.count();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}
