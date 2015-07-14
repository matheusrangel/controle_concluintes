package dao;

import java.util.List;

import model.TipoTCC;

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
}
