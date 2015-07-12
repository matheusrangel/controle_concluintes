package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Processo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String numero;
	
	@OneToOne
	private Concluinte concluinte;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	private Professor orientador;
	
	@OneToMany
	private List<Professor> banca;
	
	@OneToMany
	private List<Evento> eventos;

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

	public Professor getOrientador() {
		return orientador;
	}

	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}

	public List<Professor> getBanca() {
		return banca;
	}

	public void setBanca(List<Professor> banca) {
		this.banca = banca;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((banca == null) ? 0 : banca.hashCode());
		result = prime * result
				+ ((concluinte == null) ? 0 : concluinte.hashCode());
		result = prime * result + ((eventos == null) ? 0 : eventos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((orientador == null) ? 0 : orientador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (banca == null) {
			if (other.banca != null)
				return false;
		} else if (!banca.equals(other.banca))
			return false;
		if (concluinte == null) {
			if (other.concluinte != null)
				return false;
		} else if (!concluinte.equals(other.concluinte))
			return false;
		if (eventos == null) {
			if (other.eventos != null)
				return false;
		} else if (!eventos.equals(other.eventos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (orientador == null) {
			if (other.orientador != null)
				return false;
		} else if (!orientador.equals(other.orientador))
			return false;
		return true;
	}
	
	
}
