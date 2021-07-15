package br.com.clinicaanimal.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.clinicaanimal.persistencia.exception.DaoException;

public abstract class Dao<T> implements IDao<T> {
	
	private EntityManager entityManager;

	/**********
	 * NO QUERY
	 *********/

	/*----------------------------
	 * Inserir - Alterar - Excluir
	 * ---------------------------*/

	@Override
	public void insert(T obj) throws DaoException {		
		entityManager.persist(obj);		
	}

	@Override
	public void delete(T obj) throws DaoException {		
		obj = entityManager.merge(obj);
		entityManager.remove(obj);
	}

	@Override
	public void update(T obj) throws DaoException {
		entityManager.merge(obj);
	}

	/*-----------------------------------------------------------
	 * Consulta de um unico resultado passando uma chave primaria
	 * ---------------------------------------------------------*/

	@Override
	public T find(Class<T> t, Long pk) throws DaoException {
		return entityManager.find(t, pk);
	}

	@Override
	public T find(Class<T> t, Integer pk) throws DaoException {
		return entityManager.find(t, pk);
	}

	@Override
	public T find(Class<T> t, String pk) throws DaoException {
		return entityManager.find(t, pk);
	}

	/*************
	 * NAMEDQUERY
	 *************/
	//paginacao
	@SuppressWarnings("unchecked")
	public List<T> listPaginationNamedQuery(String namedQuery, List<Object> params, int first, int pageSize) 
			throws DaoException{		
		Query query = entityManager.createNamedQuery(namedQuery);		
		
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {				
				if(params.get(i) instanceof ArrayList){
					query.setParameter("names", params.get(i));
				
				}else{
					query.setParameter("param" + i, params.get(i));
				}
			}
			
		}
		query.setFirstResult(first);
		query.setMaxResults(pageSize);		
		return query.getResultList();
	}
	
	//total da paginacao	
	public int maxResultNamedQuery(String namedQuery, List<Object> params) throws DaoException{
		Query query = entityManager.createNamedQuery(namedQuery);
		
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				if(params.get(i) instanceof ArrayList){
					query.setParameter("names", params.get(i));
				
				}else{
					query.setParameter("param" + i, params.get(i));
				}
			}
		}
		return Integer.parseInt(query.getSingleResult().toString());
	}
	
	
	/*------------------------------
	 * Execute com hql e namedQuery.
	 ------------------------------*/
	@Override
	public boolean executeNamedQuery(String namedQuery, List<Object> params) throws DaoException {
		Query query = entityManager.createNamedQuery(namedQuery);
		
		for (int i = 0; i < params.size(); i++) {
			query.setParameter("param" + i, params.get(i)).toString();
			
		}
		
		return query.executeUpdate() == 1 ? true : false;
	}

	/*---------------------------------------
	 * list - retorna uma lista de objetos.
	 * Lista tudo sem precisar de parametros.
	 * - Utilizando hql e namedQuery.
	 ---------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(String namedQuery) throws DaoException {
		Query query = entityManager.createNamedQuery(namedQuery);		
		return query.getResultList();
	}

	/*-------------------------------------
	 * list - retorna uma lista de objetos.
	 * Lista tudo (ou uma lista limitada)
	 * usando parametros.
	 * - Utilizando hql e namedQuery.
	 --------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(String namedQuery, List<Object> params) throws DaoException {
		Query query = entityManager.createNamedQuery(namedQuery);
		
		for (int i = 0; i < params.size(); i++) {
			if(params.get(i) instanceof ArrayList){				
				query.setParameter("param" + i, params.get(i));
			
			}else{
				query.setParameter("param" + i, params.get(i));
			}
		}

		return query.getResultList();
	}

	/*------------------------------------------------------------------
	 * Retorna um unico objeto
	 * - Utilizando hql e namedQuery.
	 * - O parametro da busca deverah ser feito por uma chave unica, 
	 *   ou na certeza de haver no banco um unico valor para o campo, se
	 *   isto nao for observado serah causado uma exception.
	 * - Utilizando hql e namedQuery.
	 ------------------------------------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public T singleResult(String namedQuery, List<Object> params) throws DaoException {
		Object object = new Object();
		Query query = entityManager.createNamedQuery(namedQuery);
		
		for (int i = 0; i < params.size(); i++) {
			query.setParameter("param" + i, params.get(i));
		}

		try {
			object = query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

		return (T) object;
	}
	
	
	/*--------------------------------------------------------------
	 * Retorna um objeto unico de uma expressao HQL
	 * - quando queremos o ultimo registro baseado na expressao.
	 * - exemplo de uma namedQuery:
	 *   "from Pessoa p where p.bairro =:param0 order by p.nome DESC".
	 *   isto irah retornar a ultima pessoa de uma lista ordeada pelo
	 *   nome, que mora em um bairro passado no parametro.
	 ---------------------------------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public T minMaxFromExpression(String namedQuery, List<Object> params) throws DaoException {
		Object object = new Object();
		Query query = entityManager.createNamedQuery(namedQuery);
		
		for (int i = 0; i < params.size(); i++) {
			query.setParameter("param" + i, params.get(i));
		}
		
		query.setMaxResults(1);

		try {
			object = query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

		return (T) object;
	}

	/*************
	 * NATIVEQUERY
	 *************/

	/*-------------------------------------
	 * Inserir - Alterar - Excluir
	 * Utilizando sql nativa (nativeQuery).	
	 -------------------------------------*/
	@Override
	public boolean executeNativeQuery(String nativeQuery, List<Object> params) throws DaoException {
		Query query = entityManager.createNativeQuery(nativeQuery);
		
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter("param" + i, params.get(i));
			}
		}
		return query.executeUpdate() == 1 ? true : false;
	}

	/*-------------------------------------
	 * Simples execute.
	 * Utilizando sql nativa (nativeQuery).	
	 -------------------------------------*/
	@Override
	public boolean executeNativeQuery(String nativeQuery) throws DaoException {
		Query query = entityManager.createNativeQuery(nativeQuery);
		return query.executeUpdate() == 1 ? true : false;
	}
	
	/*-------------------------------------
	 * list - retorna uma lista de objetos.
	 * Lista tudo sem usar parametros.
	 * Utilizando sql nativa (nativeQuery).
	 -------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(T t, String nativeQuery) throws DaoException {
		Query query = entityManager.createNativeQuery(nativeQuery, t.getClass());
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> listNativeQuery(String nativeQuery) throws DaoException {
		Query query = entityManager.createNativeQuery(nativeQuery);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> listNativeQuery(T t, String nativeQuery, List<Object> params) throws DaoException{
		Query query = entityManager.createNativeQuery(nativeQuery, t.getClass());
				
		for (int i = 0; i < params.size(); i++) {
			query.setParameter("param" + i, params.get(i));
		}
		
		return query.getResultList();
	}

	/*-----------------------------------------------------
	 * Maximo de uma expressao: nativeQuery com parametros. 
	 -----------------------------------------------------*/
	@Override
	public int maxResult(String nativeQuery, List<Object> params) throws DaoException{
		Query query = entityManager.createNativeQuery(nativeQuery);
		
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter("param" + i, params.get(i));
			}
		}
		return Integer.parseInt(query.getSingleResult().toString());
	}
	
	/*-----------------------------------------------------
	 * Maximo de uma expressao: nativeQuery sem parametros. 
	 -----------------------------------------------------*/
	@Override
	public int maxResult(String nativeQuery) throws DaoException{
		Query query = entityManager.createNativeQuery(nativeQuery);	
		return Integer.parseInt(query.getSingleResult().toString());
	}
	
	
	/*-----------------------------------------------------
	 * Maximo de uma expressao: nativeQuery com parametros. 
	 -----------------------------------------------------*/
	@Override
	public Long getSequence(String sequenceName) throws DaoException{
		Query query = entityManager.createNativeQuery("select " + sequenceName + ".nextval FROM DUAL");
		entityManager.flush();
		return Long.parseLong(query.getSingleResult().toString());
	}
	

	/*-------------------------------------
	 * list - retorna uma lista de objetos.
	 * Utilizando sql nativa (nativeQuery).
	 -------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(T t, String nativeQuery, List<Object> params) throws DaoException {
		Query query = entityManager.createNativeQuery(nativeQuery, t.getClass());
				
		for (int i = 0; i < params.size(); i++) {
			query.setParameter("param" + i, params.get(i));
		}

		return query.getResultList();		
	}

	/*----------------------------------------
	 * list - retorna uma lista de objetos.
	 * Utilizando sql nativa (nativeQuery).
	 * Sem utilização de classe como parametro
	 ----------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public List<T> listNativeQuery(String nativeQuery, List<Object> params) throws DaoException {
		Query query = entityManager.createNativeQuery(nativeQuery);
				
		for (int i = 0; i < params.size(); i++) {
			query.setParameter("param" + i, params.get(i));
		}

		return query.getResultList();		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> listObjectNativeQuery(String nativeQuery, List<Object> params) throws DaoException {
		Query query = entityManager.createNativeQuery(nativeQuery);
				
		if(params != null && params.size() > 0){
			for (int i = 0; i < params.size(); i++) {
				
				if(params.get(i) instanceof ArrayList){
					query.setParameter("names", params.get(i));
					
				}else{
					query.setParameter("param" + i, params.get(i));
				}
			}
		}

		return query.getResultList();		
	}	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> listObjectNativeQuery(String nativeQuery) throws DaoException {
		Query query = entityManager.createNativeQuery(nativeQuery);
		return query.getResultList();		
	}	

	/* ------------------------------------------------ 
	 * nativeQuery com paginacao - lista com parametros.
	 * -----------------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public List<T> listPaginationNativeQuery(T t, String nativeQuery, List<Object> params, int first, int pageSize) 
			throws DaoException{
		
		Query query = entityManager.createNativeQuery(nativeQuery, t.getClass());
				
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter("param" + i, params.get(i));
			}
		}
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		return query.getResultList();		
	}

	/*-------------------------------------------------
	 * Consulta de unico resultado - retorna um objeto.	 
	 * Utilizando sql nativa (nativeQuery).
	 -------------------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public T singleResult(T t, String nativeQuery, List<Object> params) throws DaoException {
		Object object = new Object();
		Query query = entityManager.createNativeQuery(nativeQuery, t.getClass());
		
		for (int i = 0; i < params.size(); i++) {
			query.setParameter("param" + i, params.get(i));
		}

		try {
			object = query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

		return (T) object;
	}

	/*--------------------------------------------------------------------------------------
	 * Consulta de unico resultado - retorna um objeto.	Utilizando sql nativa (nativeQuery).
	 -------------------------------------------------------------------------------------*/
	@Override
	@SuppressWarnings("unchecked")
	public T singleResultNativeQuery(String nativeQuery, List<Object> params) throws DaoException {
		Object object = new Object();
		Query query = entityManager.createNativeQuery(nativeQuery);
		
		for (int i = 0; i < params.size(); i++) {
			query.setParameter("param" + i, params.get(i));
		}
		try {
			object = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return (T) object;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T singleResultNativeQuery(String nativeQuery) throws DaoException {
		Object object = new Object();
		Query query = entityManager.createNativeQuery(nativeQuery);		
		try {
			object = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return (T) object;
	}

	/**************************************************************
	 * MANIPULAÇÃO DO ENTITY MANAGER - Apenas objetos persistentes.
	 *************************************************************/
	/*----------------------------------------------------------------
	 * Apagar todos os objetos que estao gerenciados no EntityManager.
	 * --------------------------------------------------------------*/
	@Override
	public void clear() {
		entityManager.clear();
	}

	/*--------------------------
	 * Atualizar o EntityManager
	 * ------------------------*/
	@Override
	public void refresh(Object obj, LockModeType lockModeType) {
		if (entityManager.contains(obj)) entityManager.refresh(obj, lockModeType);
	}
	
	@Override
	public void refresh(Object obj) {
		if (entityManager.contains(obj)) entityManager.refresh(obj);
	}

	/*-----------------------------------------------------
	 * Verificar se um determinado objeto estah gerenciado.
	 * ---------------------------------------------------*/
	@Override
	public boolean isManaged(Object obj) {
		return entityManager.contains(obj);
	}

	/*-------------------------------------------
	 * Bloquear/desbloquear um objeto para edição
	 * -----------------------------------------*/
	@Override
	public void lock(Object obj, LockModeType loModeType) {
		entityManager.lock(obj, loModeType);
	}

	/*------------------------------------------------------
	 * Sincroniza objetos persistentes com o banco de dados.
	 * ----------------------------------------------------*/
	@Override
	public void flush() {
		entityManager.flush();
	}

	/*--------------------------------------
	 * Desassocia o objeto do entityManager.
	 * ------------------------------------*/
	@Override
	public void detach(Object obj) {
		entityManager.detach(obj);
	}

	/*-------------------------
	 * Obtem um objeto managed.
	 * -----------------------*/
	@Override
	public Object get(Class<T> t, Object obj) {
		return entityManager.getReference(t, obj);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}


