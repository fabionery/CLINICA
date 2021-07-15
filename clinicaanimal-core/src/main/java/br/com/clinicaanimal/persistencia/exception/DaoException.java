package br.com.clinicaanimal.persistencia.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException(String message) {
		super(message);
	}
}

