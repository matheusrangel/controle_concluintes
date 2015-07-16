package dao;

import java.util.List;


import model.TCC;

public class TCCDAO extends GenericDAO<TCC> {
	
	public TCCDAO() {
		super();
	}
	
	public List<TCC> findAll() {
		return findAll(TCC.class);
	}

	public TCC findById(Long id) {
		return super.findByField(TCC.class, "id", id);
	}
	
	public TCC findByAutor(Long id) {
		return super.findByField(TCC.class, "autor", id);
	}
}
