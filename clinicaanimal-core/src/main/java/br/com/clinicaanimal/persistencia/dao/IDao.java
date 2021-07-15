package br.com.clinicaanimal.persistencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import br.com.clinicaanimal.persistencia.exception.DaoException;

public abstract interface IDao<T> {

	/**********
	 * NO QUERY
	 *********/	
	public void insert(T obj) throws DaoException;	
	public void delete(T obj) throws DaoException;
	public void update(T obj) throws DaoException;	
	public T find(Class<T> t, Long pk) throws DaoException;
	public T find(Class<T> t, Integer pk) throws DaoException;
	public T find(Class<T> t, String pk) throws DaoException;

	/*************
	 * NAMEDQUERY
	 *************/	
	public boolean executeNamedQuery(String namedQuery, List<Object> params) throws DaoException;
	public List<T> list(String namedQuery) throws DaoException;	
	public List<T> list(String namedQuery, List<Object> params) throws DaoException;
	public T singleResult(String namedQuery, List<Object> params) throws DaoException;
	public T minMaxFromExpression(String namedQuery, List<Object> params)  throws DaoException;
	public List<T> listPaginationNamedQuery(String namedQuery, List<Object> params, int first, int pageSize) throws DaoException;
	public int maxResultNamedQuery(String namedQuery, List<Object> params) throws DaoException;
	
	/*************
	 * NATIVEQUERY
	 *************/
	public boolean executeNativeQuery(String nativeQuery, List<Object> params) throws DaoException;	
	public boolean executeNativeQuery(String nativeQuery) throws DaoException;
	public List<T> list(T t, String nativeQuery) throws DaoException;	
	public List<T> listNativeQuery(String nativeQuery) throws DaoException;
	public int maxResult(String nativeQuery, List<Object> params) throws DaoException;
	public int maxResult(String nativeQuery) throws DaoException;
	public List<T> list(T t, String nativeQuery, List<Object> params) throws DaoException;
	public List<T> listNativeQuery(String nativeQuery, List<Object> params) throws DaoException;
	public List<T> listNativeQuery(T t, String nativeQuery, List<Object> params) throws DaoException;
	
	public List<T> listPaginationNativeQuery(T t, String nativeQuery, 
			List<Object> params, int first, int pageSize) throws DaoException;
	
	public T singleResult(T t, String nativeQuery, List<Object> params) throws DaoException;	
	public T singleResultNativeQuery(String nativeQuery, List<Object> params) throws DaoException;
	public T singleResultNativeQuery(String nativeQuery) throws DaoException;
	public Long getSequence(String sequenceName) throws DaoException;
	public List<Object[]> listObjectNativeQuery(String nativeQuery, List<Object> params) throws DaoException;
	public List<Object[]> listObjectNativeQuery(String nativeQuery) throws DaoException;
	
	
	/*******************************
	 * MANIPULAÇÃO DO ENTITY MANAGER
	 *******************************/	
	public void clear();	
	public void refresh(Object obj, LockModeType lo);
	public void refresh(Object obj);
	public boolean isManaged(Object obj);	
	public void lock(Object obj, LockModeType loModeType);
	public void flush();	
	public void detach(Object obj);
	public Object get(Class<T> t, Object obj);	
	public EntityManager getEntityManager();
	public void setEntityManager(EntityManager entityManager);

}