package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GenericDAO<T> {
	protected static EntityManager manager;

	protected GenericDAO(){
		manager = getManager();
	}

	protected static EntityManager getManager(){
		if(manager==null){
			manager = Persistence.createEntityManagerFactory("concluintes").createEntityManager();
		}
		return manager;
	}


	public void persist(T obj){
		begin();
		manager.persist(obj);
		commit();
	}
	public void remove(T obj) {
		begin();
		manager.remove(obj);
		commit();
	}
	public void update(T obj){
		begin();
		manager.merge(obj);
		commit();
	}
	public void refresh(T obj){
		begin();
		manager.refresh(obj);
		commit();
	}
	protected T find(Class<T> classe, Object chave){
		return manager.find(classe, chave);
	}
	@SuppressWarnings("unchecked")
	protected List<T> findAll(Class<T> classe){
		String nome = classe.getName();
		int i = nome.lastIndexOf(".");
		nome=nome.substring(i+1);
		Query query = manager.createQuery("select x from " + nome + " x");
		return (List<T>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findAllByField(Class<T> classe, String campo, String valor)   {
		String nome = classe.getName();
		int i = nome.lastIndexOf(".");
		nome=nome.substring(i+1);
		Query query = manager.createQuery("select x from " + nome + " x " +
				"where x." + campo + " = '" + valor + "'");
		return (List<T>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected T findByField(Class<T> classe, String campo, Long id)    {
		String nome = classe.getName();
		int i = nome.lastIndexOf(".");
		nome=nome.substring(i+1);
		Query query = manager.createQuery("select x from " + nome + " x " +
				"where x." + campo + " = '" + id + "'");
		return (T) query.getSingleResult();
	}

	public void close(){
		manager = null;
	}

	public static void begin(){
		manager.getTransaction().begin();
	}
	public static void commit(){
		manager.getTransaction().commit();
	}
	public static void rollback(){
		manager.getTransaction().rollback();
	}

	//----------------------------------------------------------
	//----------------------- USO DE JPQL ----------------------
	//----------------------------------------------------------
	public  Object findByQuery(String consulta){            
		try{
			Query q = manager.createQuery(consulta);
			return (Object) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
		catch(NonUniqueResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public  List<T> findAllByQuery(String consulta){                
		try{
			Query q = manager.createQuery(consulta);
			return q.getResultList();
		}
		catch(NoResultException e){
			return null;
		}
		catch(NonUniqueResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public  List<T> findAgregateByQuery(String consulta){
		Query q = manager.createQuery(consulta);
		return q.getResultList();
	}

	public int updadeAll(String consulta) {
		begin();
		Query q = manager.createQuery(consulta);
		int linhas = q.executeUpdate();
		commit();
		return linhas;
	}

}

