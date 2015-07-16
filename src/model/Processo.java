package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import auxiliar.StatusProcesso;

@Entity
public class Processo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String numero;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
	private Concluinte concluinte;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Evento> eventos;
	
	@Transient
	private StatusProcesso status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Concluinte getConcluinte() {
		return concluinte;
	}

	public void setConcluinte(Concluinte concluinte) {
		this.concluinte = concluinte;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public StatusProcesso getStatus() {
		if (this.eventos != null && !this.eventos.isEmpty()) {
			return (this.eventos.get(this.eventos.size()-1)).getStatusEnum();
		}
		return null;
	}

	public void setStatus(StatusProcesso status) {
		this.status = status;
	}

	
}
