package dao;

import java.util.List;

import model.Evento;

public class EventoDAO extends GenericDAO<Evento>{
	
	public EventoDAO() {
		super();
	}
	
	public List<Evento> findAll() {
		return findAll(Evento.class);
	}

	public Evento findById(Long id) {
		return super.findByField(Evento.class, "id", id);
	}
}
