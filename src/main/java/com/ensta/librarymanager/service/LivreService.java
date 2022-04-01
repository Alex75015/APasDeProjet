package com.ensta.librarymanager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreService implements ILivreService {

	private LivreDao livreDao = LivreDao.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();

	////////////////////////
	// SINGLETON LIVREDAO DEBUT
	static LivreService instance; // pour implémenter singleton cf cours

	private LivreService() { // on met le constructeur private : pas possible d'init sans passer par
								// getInstance

	}

	public static LivreService getInstance() {
		if (instance == null) {
			instance = new LivreService();
		}
		return instance;
	}
	// SINGLETON FIN
	///////////////////

	public List<Livre> getList() throws ServiceException {
		try {

			return livreDao.getList();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public Livre getById(int id) throws ServiceException {

		try {

			return livreDao.getById(id);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		try {
			List<Livre> livres = getList();
			List<Livre> livresDispo = new ArrayList<>();
			for(int i=0; i < livres.size(); i++) {
				if(empruntService.isLivreDispo(livres.get(i).getId())) {
					livresDispo.add(livres.get(i));
				}
			}
			return livresDispo;

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public int create(Livre livre) throws ServiceException {
		try {
			if (livre.getTitre() == "") {
				throw new ServiceException("Le titre est vide !");
			}

			livreDao.create(livre);

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
	public void update(Livre livre) throws ServiceException {
		try {
			if (livre.getTitre() == "") {
				throw new ServiceException("Le titre est vide !");
			}

			livreDao.update(livre);

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

			livreDao.delete(id);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	@Override
	public int count() throws ServiceException {
		try {

			return livreDao.count();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}

	}

}
