package br.com.clinicaanimal.persistencia.facade.impl;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaanimal.persistencia.dao.IDao;
import br.com.clinicaanimal.persistencia.facade.IAnimalFacade;

@Named
@Stateless
public class AnimalFacade<T> extends Facade<T> implements IAnimalFacade<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IDao<T> dao;

	public void initDao() {
		super.setDao(dao);
	}

}
