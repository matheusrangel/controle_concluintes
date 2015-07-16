package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import auxiliar.SituacaoConcluinte;

@Entity
public class Concluinte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=15)
	private String matricula;
	
	@Column(length=80)
	private String nome;
	
	@Column(length=40)
	private String email;
	
	@Column(length=20)
	private String telefone;
	
	@Column
	private Integer status;
	
	private SituacaoConcluinte situacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public SituacaoConcluinte getSituacao() {
		return SituacaoConcluinte.values()[getStatus()];
	}

	public void setSituacao(SituacaoConcluinte situacao) {
		this.situacao = situacao;
		this.status = this.situacao.getValue();
	}

	
	
	
}
