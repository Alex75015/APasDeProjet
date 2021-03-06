package com.ensta.librarymanager.dao;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;

public interface ILivreDao {
	public List<Livre> getList() throws DaoException;
	public Livre getById(int id) throws DaoException;
	public int create(Livre livre) throws DaoException;
	public void update(Livre livre) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
