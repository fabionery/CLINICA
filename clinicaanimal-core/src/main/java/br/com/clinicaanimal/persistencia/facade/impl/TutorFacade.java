package br.com.clinicaanimal.persistencia.facade.impl;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaanimal.persistencia.dao.IDao;
import br.com.clinicaanimal.persistencia.facade.ITutorFacade;

@Named
@Stateless
public class TutorFacade<T> extends Facade<T> implements ITutorFacade<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IDao<T> dao;

	public void initDao() {
		super.setDao(dao);
	}

}
