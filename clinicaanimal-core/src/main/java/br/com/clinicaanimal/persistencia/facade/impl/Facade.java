package br.com.clinicaanimal.persistencia.facade.impl;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.LockModeType;

import br.com.clinicaanimal.persistencia.dao.IDao;
import br.com.clinicaanimal.persistencia.exception.DaoException;
import br.com.clinicaanimal.persistencia.facade.IFacade;

public abstract class Facade<T> implements IFacade<T>{
	
	@Inject
	private IDao<T> dao;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void incluir(T obj) throws DaoException {
		dao.insert(obj);
		dao.flush();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluir(T obj) throws DaoException {
		dao.delete(obj);	
		dao.flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Object alterarAtualizar(T obj) throws DaoException {
		dao.update(obj); //Realiza a persistencia.
		dao.flush(); //Sincroniza o banco de dados com os objetos gerenciados em entityManager.
		
		try {
			
			/*
			 * A rotina abaixo sincroniza o objeto que veio da view com o que estah gerenciado.
			 * O resultado eh que o metodo devolve o objeto sincronizado com banco de dados e entityManager,
			 * e tambem sincronizado com o objeto da view, bastando apenas, na view, igualar o objeto.
			 */
			
//			Long id = (Long) obj.getClass().getMethod("getId").invoke(obj);
			if(obj.getClass().getMethod("getId").invoke(obj)!=null){
				Class<?> partTypesGet[] = new Class[2]; 
				partTypesGet[0] = Class.class;  
				partTypesGet[1] = Object.class;             
				Object argListGet[] = new Object[2];
				argListGet[0] = obj.getClass();
				argListGet[1] = obj.getClass().getMethod("getId").invoke(obj);
								
				Object object = dao.get((Class<T>) obj.getClass(), obj.getClass().getMethod("getId").invoke(obj));	
				dao.refresh(object); //Atualiza o objeto.
				return object; //Retorna o objeto sincronizado e atualizado.				
			
			}else{
				return obj;
			}			
		
		} catch (Exception e) {			
			new DaoException(e.getMessage());
		} 
		
		return null;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterar(T obj) throws DaoException {
		dao.update(obj); //Realiza a persistencia.
		dao.flush();
	}
	
	@Override
	public void incluirSequenceManual(T obj) throws DaoException {
		obj = dao.getEntityManager().merge(obj); //Realiza a persistencia.
		dao.flush();
		//dao.getEntityManager().persist(obj);
		//dao.flush();
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterarRequiresNew(T obj) throws DaoException {
		dao.update(obj); //Realiza a persistencia.
		dao.flush();
	}

	@Override
	public T recuperar(Class<T> t, Long pk) throws DaoException {
		return dao.find(t, pk);
	}

	@Override
	public T recuperar(Class<T> t, Integer pk) throws DaoException {
		return dao.find(t, pk);
	}

	@Override
	public T recuperar(Class<T> t, String pk) throws DaoException {
		return dao.find(t, pk);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean executarNamedQuery(String namedQuery, List<Object> params) throws DaoException {
		boolean b = dao.executeNativeQuery(namedQuery, params);
		dao.flush();
		return b;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> listar(String namedQuery) throws DaoException {		
		return dao.list(namedQuery);
	}

	@Override
	public List<T> listar(String namedQuery, List<Object> params) throws DaoException {
		return dao.list(namedQuery, params);
	}

	@Override
	public T recuperar(String namedQuery, List<Object> params) throws DaoException {
		return dao.singleResult(namedQuery, params);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean executarNativeQuery(String nativeQuery, List<Object> params) throws DaoException {
		boolean b = dao.executeNativeQuery(nativeQuery, params);
		dao.flush();
		return b;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean executarNativeQuery(String nativeQuery) throws DaoException {
		boolean b = dao.executeNativeQuery(nativeQuery);
		dao.flush();
		return b;
	}

	@Override
	public List<T> listar(T t, String nativeQuery) throws DaoException {
		return dao.list(t, nativeQuery);
	}
	
	@Override
	public List<T> listarNativeQuery( String nativeQuery) throws DaoException {
		return dao.listNativeQuery(nativeQuery);
	}
	
	@Override
	public List<T> listarComNativeQuery(T t, String nativeQuery, List<Object> params) throws DaoException{
		return dao.listNativeQuery(t, nativeQuery, params);
	}
	
	@Override
	public List<T> listarPaginandoNativeQuery(T t, String nativeQuery, List<Object> params, int first, int pageSize) throws DaoException {
		return dao.listPaginationNativeQuery(t, nativeQuery, params, first, pageSize);		
	}

	@Override
	public int maxResult(String nativeQuery, List<Object> params) throws DaoException {
		return dao.maxResult(nativeQuery, params);
	}
	
	@Override
	public int maxResult(String nativeQuery) throws DaoException{
		return dao.maxResult(nativeQuery);
	}

	@Override
	public List<T> listar(T t, String nativeQuery, List<Object> params) throws DaoException {
		return dao.list(t, nativeQuery, params);
	}

	@Override
	public List<T> listarComNativeQuery(String nativeQuery, List<Object> params) throws DaoException {
		return dao.listNativeQuery(nativeQuery, params);
	}

	@Override
	public T recuperarComNativeQuery(T t, String nativeQuery, List<Object> params) throws DaoException {
		return dao.singleResult(t, nativeQuery, params);
	}

	@Override
	public T recuperarComNativeQuery(String nativeQuery, List<Object> params) throws DaoException {
		return dao.singleResultNativeQuery(nativeQuery, params);
	}
	
	@Override
	public T recuperarComNativeQuery(String nativeQuery) throws DaoException {
		return dao.singleResultNativeQuery(nativeQuery);
	}
	
	@Override
	public Long getSequence(String sequenceName) throws DaoException{
		return dao.getSequence(sequenceName);
	}
	
	@Override
	public List<Object[]> listarObjetosComNativeQuery(String nativeQuery, List<Object> params) throws DaoException{
		return dao.listObjectNativeQuery(nativeQuery, params);
	}
	
	@Override
	public List<Object[]> listarObjetosComNativeQuery(String nativeQuery) throws DaoException{
		return dao.listObjectNativeQuery(nativeQuery);
	}
	
	@Override
	public T recuperarMinMaxDeUmaExpressao(String namedQuery, List<Object> params) throws DaoException {
		return dao.minMaxFromExpression(namedQuery, params);
	}

//	@Override
//	public IDataModelPaginator<?> initPaginator(T t) {
//		return new DataModelPaginator<T>(t, dao);
//	}

//	@Override
//	public void listarComPaginador(IDataModelPaginator<?> dataModelPaginator) throws DaoException {
//		dataModelPaginator.setFilters(null);
//		dataModelPaginator.listPaginate();	
//	}

//	@Override
//	public void paginarComPaginador(IDataModelPaginator<?> dataModelPaginator) throws DaoException {
//		dataModelPaginator.listPaginate();
//	}

//	@Override
//	public void incluirComPaginador(IDataModelPaginator<?> dataModelPaginator, T obj) throws DaoException {
//		dao.insert(obj);
//		dao.flush(); //Sincroniza com o banco antes de paginar.
//		dataModelPaginator.refreshAfterInsert(); //Atualiza o paginador.
//	}

//	@Override
//	public void excluirComPaginador(IDataModelPaginator<?> dataModelPaginator, T obj) throws DaoException {
//		dao.delete(obj);
//		dao.flush(); //Sincroniza com o banco antes de paginar.
//		dataModelPaginator.refreshAfterDelete(); //Atualiza o paginador.
//	}

//	@Override
//	public void alterarComPaginador(IDataModelPaginator<?> dataModelPaginator, T obj) throws DaoException {
//		Object object = this.alterarAtualizar(obj); //Altera e retorna o objeto sincronizado e atualizado.
//		dataModelPaginator.refreshAfterUpdate(object); //Atualiza o paginador.
//	}
//
//	@Override
//	public void localizarComPaginador(IDataModelPaginator<?> dataModelPaginator, Map<String, String> filters)
//			throws DaoException {
//		
//		dataModelPaginator.setIndexPage(1);
//		dataModelPaginator.setFilters(filters);
//		dataModelPaginator.listPaginate();
//	}
	
	@Override
	public void clear() {
		dao.clear();		
	}
	
	@Override
	public boolean isManaged(Object obj) {
		return dao.isManaged(obj);
	}
	
	@Override
	public void flush() {
		dao.flush();		
	}
	
	@Override
	public void lock(Object obj, LockModeType lockModeType){
		dao.lock(obj, lockModeType);
	}
	
	@Override
	public void refresh(Object obj){
		dao.refresh(obj);
	}
	
	@Override
	public void refresh(Object obj, LockModeType lockModeType){
		dao.refresh(obj, lockModeType);
	}

	@Override
	public void detach(Object obj) {
		dao.detach(obj);		
	}

	@Override
	public Object get(Class<T> t, Object obj) {
		return dao.get(t, obj);
	}

	@Override
	public IDao<T> getDao() {
		return dao;
	}

	@Override
	public void setDao(IDao<T> dao) {
		this.dao = dao;
	}

}
