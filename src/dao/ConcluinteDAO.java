package dao;

import java.util.List;

import javax.persistence.Query;

import model.Concluinte;

public class ConcluinteDAO extends GenericDAO<Concluinte> {
	
	public ConcluinteDAO() {
		super();
	}
	
	public List<Concluinte> findAll() {
		return findAll(Concluinte.class);
	}

	public Concluinte findById(Long id) {
		return super.findByField(Concluinte.class, "id", id);
	}
	
	public Concluinte findByMatricula(String matricula){
		String classe = Concluinte.class.getName();
		String campo = "matricula";
		int i = classe.lastIndexOf(".");
		classe=classe.substring(i+1);
		Query query = manager.createQuery("select x from " + classe + " x " +
				"where x." + campo + " = '" + matricula + "'");
		List<Concluinte> concluintes =  query.getResultList();
		if(concluintes.size() > 0){
			return concluintes.get(0);
		} else {
			return null;
		}

	}
}
