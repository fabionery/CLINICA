package br.com.clinicaanimal.persistencia.facade;

import java.util.List;

import javax.persistence.LockModeType;

import br.com.clinicaanimal.persistencia.dao.IDao;
import br.com.clinicaanimal.persistencia.exception.DaoException;

public abstract interface IFacade<T> {
	
	/*----------------------------
	 * Inserir - Alterar - Excluir
	 * ---------------------------*/
	public void incluir(T obj) throws DaoException;
	public void excluir(T obj) throws DaoException;
	public Object alterarAtualizar(T obj) throws DaoException;
	public void alterar(T obj) throws DaoException;
	public void alterarRequiresNew(T obj) throws DaoException;
	public void incluirSequenceManual(T obj) throws DaoException;	
	
	/*-----------------------------------------------------------
	 * Consulta de um unico resultado passando uma chave primaria
	 * ---------------------------------------------------------*/
	public T recuperar(Class<T> t, Long pk) throws DaoException;
	public T recuperar(Class<T> t, Integer pk) throws DaoException;
	public T recuperar(Class<T> t, String pk) throws DaoException;

	/*************
	 * NAMEDQUERY
	 *************/
	/*------------------------------
	 * Execute com hql e namedQuery.
	 ------------------------------*/
	public boolean executarNamedQuery(String namedQuery, List<Object> params) throws DaoException;

	/*---------------------------------------
	 * list - retorna uma lista de objetos.
	 * Lista tudo sem precisar de parametros.
	 * - Utilizando hql e namedQuery.
	 ---------------------------------------*/	
	public List<T> listar(String namedQuery) throws DaoException;

	/*-------------------------------------
	 * list - retorna uma lista de objetos.
	 * Lista tudo (ou uma lista limitada)
	 * usando parametros.
	 * - Utilizando hql e namedQuery.
	 --------------------------------------*/
	public List<T> listar(String namedQuery, List<Object> params) throws DaoException;

	/*------------------------------------------------------------------
	 * Retorna um unico objeto
	 * - Utilizando hql e namedQuery.
	 * - O parametro da busca deverah ser feito por uma chave unica, 
	 *   ou na certeza de haver no banco um unico valor para o campo, se
	 *   isto nao for observado serah causado uma exception.
	 * - Utilizando hql e namedQuery.
	 ------------------------------------------------------------------*/
	public T recuperar(String namedQuery, List<Object> params) throws DaoException;
	
	/*--------------------------------------------------------------
	 * Retorna um objeto unico de uma expressao HQL
	 * - quando queremos o ultimo registro baseado na expressao.
	 * - exemplo de uma namedQuery:
	 *   "from Pessoa p where p.bairro =:param0 order by p.nome DESC".
	 *   isto irah retornar a ultima pessoa de uma lista ordeada pelo
	 *   nome, que mora em um bairro passado no parametro.
	 ---------------------------------------------------------------*/
	public T recuperarMinMaxDeUmaExpressao(String namedQuery, List<Object> params) throws DaoException;

	/*************
	 * NATIVEQUERY
	 *************/

	/*-------------------------------------
	 * Inserir - Alterar - Excluir
	 * Utilizando sql nativa (nativeQuery).	
	 -------------------------------------*/
	public boolean executarNativeQuery(String nativeQuery, List<Object> params) throws DaoException;

	/*-------------------------------------
	 * Simples execute.
	 * Utilizando sql nativa (nativeQuery).	
	 -------------------------------------*/
	public boolean executarNativeQuery(String nativeQuery) throws DaoException;

	/*-------------------------------------
	 * list - retorna uma lista de objetos.
	 * Lista tudo sem usar parametros.
	 * Utilizando sql nativa (nativeQuery).
	 -------------------------------------*/
	public List<T> listar(T t, String nativeQuery) throws DaoException;
	public List<T> listarNativeQuery(String nativeQuery) throws DaoException;
	
	public List<T> listarPaginandoNativeQuery(T t, String nativeQuery, 
			List<Object> params, int first, int pageSize) throws DaoException;

	/*-----------------------------------------------------
	 * Maximo de uma expressao: nativeQuery com parametros. 
	 -----------------------------------------------------*/
	public int maxResult(String nativeQuery, List<Object> params) throws DaoException;
	public int maxResult(String nativeQuery) throws DaoException;

	/*-------------------------------------
	 * list - retorna uma lista de objetos.
	 * Utilizando sql nativa (nativeQuery).
	 -------------------------------------*/
	public List<T> listar(T t, String nativeQuery, List<Object> params) throws DaoException;

	/*----------------------------------------
	 * list - retorna uma lista de objetos.
	 * Utilizando sql nativa (nativeQuery).
	 * Sem utilização de classe como parametro
	 ----------------------------------------*/
	public List<T> listarComNativeQuery(String nativeQuery, List<Object> params) throws DaoException;
	public List<T> listarComNativeQuery(T t, String nativeQuery, List<Object> params) throws DaoException;
	/*-------------------------------------------------
	 * Consulta de unico resultado - retorna um objeto.	 
	 * Utilizando sql nativa (nativeQuery).
	 -------------------------------------------------*/
	public T recuperarComNativeQuery(T t, String nativeQuery, List<Object> params) throws DaoException;
	/*--------------------------------------------------------------------------------------
	 * Consulta de unico resultado - retorna um objeto.	Utilizando sql nativa (nativeQuery).
	 -------------------------------------------------------------------------------------*/
	public T recuperarComNativeQuery(String nativeQuery, List<Object> params) throws DaoException;
	public T recuperarComNativeQuery(String nativeQuery) throws DaoException;

	public Long getSequence(String sequenceName) throws DaoException;
	
	public List<Object[]> listarObjetosComNativeQuery(String nativeQuery, List<Object> params) throws DaoException;
	public List<Object[]> listarObjetosComNativeQuery(String nativeQuery) throws DaoException;
	
	
	/*************************************
	 * Implementacao de DataModelPaginator
	 ************************************/
//	public IDataModelPaginator<?> initPaginator(T t);	
//	public void listarComPaginador(IDataModelPaginator<?> dataModelPaginator) throws DaoException;	
//	public void paginarComPaginador(IDataModelPaginator<?> dataModelPaginator) throws DaoException;	
//	public void incluirComPaginador(IDataModelPaginator<?> dataModelPaginator, T obj) throws DaoException;	
//	public void excluirComPaginador(IDataModelPaginator<?> dataModelPaginator, T obj) throws DaoException;	
//	public void alterarComPaginador(IDataModelPaginator<?> dataModelPaginator, T obj) throws DaoException;	
//	
//	public void localizarComPaginador(IDataModelPaginator<?> dataModelPaginator, Map<String, String> filters) 
//			throws DaoException;
			
	/**************************************************************
	 * MANIPULAÇÃO DO ENTITY MANAGER - Apenas objetos persistentes.
	 *************************************************************/
	/*----------------------------------------------------------------
	 * Apagar todos os objetos que estao gerenciados no EntityManager.
	 * --------------------------------------------------------------*/
	public void clear();
	
	/*-----------------------------------------------------
	 * Verificar se um determinado objeto estah gerenciado.
	 * ---------------------------------------------------*/
	public boolean isManaged(Object obj);

	/*-------------------------------------------
	 * Bloquear/desbloquear um objeto para edição
	 * -----------------------------------------*/
	public void lock(Object obj, LockModeType lockModeType);

	/*------------------------------------------------------
	 * Sincroniza objetos persistentes com o banco de dados.
	 * ----------------------------------------------------*/
	public void flush();
	
	/*-------------------------------
	 * Atualiza objetos persistentes.
	 * -----------------------------*/
	public void refresh(Object obj);
	public void refresh(Object obj, LockModeType lockModeType);

	/*--------------------------------------
	 * Desassocia o objeto do entityManager.
	 * ------------------------------------*/
	public void detach(Object obj);

	/*-------------------------
	 * Obtem um objeto managed.
	 * -----------------------*/
	public Object get(Class<T> t, Object obj);
	
	public IDao<T> getDao();
	public void setDao(IDao<T> dao);

}
