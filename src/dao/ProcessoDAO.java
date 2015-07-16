package dao;

import java.util.List;

import javax.persistence.Query;

import model.Processo;

public class ProcessoDAO extends GenericDAO<Processo> {
	
	
	public ProcessoDAO() {
		super();
	}

	public List<Processo> findAll() {
		return findAll(Processo.class);
	}

	public Processo findById(Long id) {
		return super.findByField(Processo.class, "id", id);
	}
	
	public Processo findByNome(String numero){
		String classe = Processo.class.getName();
		String campo = "numero";
		int i = classe.lastIndexOf(".");
		classe=classe.substring(i+1);
		Query query = manager.createQuery("select x from " + classe + " x " +
				"where x." + campo + " = '" + numero + "'");
		List<Processo> processos =  query.getResultList();
		if(processos.size() > 0){
			return processos.get(0);
		} else {
			return null;
		}

	}
}
