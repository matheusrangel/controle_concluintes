package dao;

import java.util.List;

import javax.persistence.Query;

import model.TipoTCC;
import model.Usuario;

public class TipoTCCDAO extends GenericDAO<TipoTCC> {
	
	public TipoTCCDAO() {
		super();
	}
	
	public List<TipoTCC> findAll() {
		return findAll(TipoTCC.class);
	}

	public TipoTCC findById(Long id) {
		return super.findByField(TipoTCC.class, "id", id);
	}
	
	public TipoTCC findByNome(String tipo){
		String classe = TipoTCC.class.getName();
		String campo = "tipo";
		int i = classe.lastIndexOf(".");
		classe=classe.substring(i+1);
		Query query = manager.createQuery("select x from " + classe + " x " +
				"where x." + campo + " = '" + tipo + "'");
		List<TipoTCC> resultado =  query.getResultList();
		if(resultado.size() > 0){
			return resultado.get(0);
		} else {
			return null;
		}

	}
}
