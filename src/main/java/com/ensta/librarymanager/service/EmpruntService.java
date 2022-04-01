package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntService implements IEmpruntService{
	
	private EmpruntDao empruntDao = EmpruntDao.getInstance();

	////////////////////////
	// SINGLETON LIVREDAO DEBUT
	static EmpruntService instance; // pour implémenter singleton cf cours

	private EmpruntService() { // on met le constructeur private : pas possible d'init sans passer par
								// getInstance

	}

	public static EmpruntService getInstance() {
		if (instance == null) {
			instance = new EmpruntService();
		}
		return instance;
	}
	// SINGLETON FIN
	///////////////////

	@Override
	public List<Emprunt> getList() throws ServiceException {
		try {

			return empruntDao.getList();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		try {

			return empruntDao.getListCurrent();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		try {

			return empruntDao.getListCurrentByMembre(idMembre);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		try {

			return empruntDao.getListCurrentByLivre(idLivre);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public Emprunt getById(int id) throws ServiceException {
		try {

			return empruntDao.getById(id);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public void create(Emprunt emprunt) throws ServiceException {
		try {
			empruntDao.create(emprunt);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		try {
			Emprunt emprunt = empruntDao.getById(id);
			emprunt.setDateRetour(LocalDate.now());
			empruntDao.update(emprunt);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public int count() throws ServiceException {
		try {

			return empruntDao.count();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		try {
			List<Emprunt> emprunts = getListCurrentByLivre(idLivre);
			if(emprunts.size() == 0) {
				return true;
			}
			return false;

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		
		try {
			List<Emprunt> emprunts = getListCurrentByMembre(membre.getId());
			int n = membre.getNombreLivreMax();
			if(emprunts.size() < n) {
				return true;
			}
			return false;

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}
