package model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TCC {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private TipoTCC tipo;
	
	@OneToOne
	private Concluinte autor;
	
	@ManyToOne
	private Professor orientador;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
	@JoinTable(name="tcc_professor", joinColumns={@JoinColumn(name="tcc_id")}, inverseJoinColumns = {@JoinColumn(name="professor_id")})
	private List<Professor> banca;
	
	@Column
	private Double nota;
	
	public TCC() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTCC getTipo() {
		return tipo;
	}

	public void setTipo(TipoTCC tipo) {
		this.tipo = tipo;
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

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Concluinte getAutor() {
		return autor;
	}

	public void setAutor(Concluinte autor) {
		this.autor = autor;
	}


}
