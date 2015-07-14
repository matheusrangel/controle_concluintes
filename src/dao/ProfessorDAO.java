package dao;

import java.util.List;

import javax.persistence.Query;

import model.Professor;

public class ProfessorDAO extends GenericDAO<Professor> {
	
	public ProfessorDAO() {
		super();
	}

	public List<Professor> findAll() {
		return findAll(Professor.class);
	}

	public Professor findById(Long id) {
		return super.findByField(Professor.class, "id", id);
	}
	
	public Professor findByNome(String nome){
		String classe = Professor.class.getName();
		String campo = "nome";
		int i = classe.lastIndexOf(".");
		classe=classe.substring(i+1);
		Query query = manager.createQuery("select x from " + classe + " x " +
				"where x." + campo + " = '" + nome + "'");
		List<Professor> professores =  query.getResultList();
		if(professores.size() > 0){
			return professores.get(0);
		} else {
			return null;
		}

	}
}
