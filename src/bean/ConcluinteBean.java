package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.model.DualListModel;

import model.Concluinte;
import model.Professor;
import model.TCC;
import model.TipoTCC;
import auxiliar.SituacaoConcluinte;
import dao.ConcluinteDAO;
import dao.ProfessorDAO;
import dao.TCCDAO;
import dao.TipoTCCDAO;

@ManagedBean(name="concluinteBean")
public class ConcluinteBean {
	private DataModel<Concluinte> model;
	private List<Concluinte> concluintes;
	private String matricula, nome, email, telefone;
	private TipoTCC tipo;
	private List<TipoTCC> tipos;
	private Professor orientador;
	private List<Professor> professoresSource;
	private List<Professor> professoresTarget;
	private DualListModel<Professor> professores;
	private Double nota;
	
	private Long matriculaSelected;
	
	@PostConstruct
	public void carregarDados() {
		TipoTCCDAO tipoTccDAO = new TipoTCCDAO();
		ProfessorDAO professorDAO = new ProfessorDAO();
		ConcluinteDAO concluinteDAO = new ConcluinteDAO();

		this.concluintes = concluinteDAO.findAll();
		this.model = new ListDataModel<Concluinte>(this.concluintes);
		
		this.tipos = tipoTccDAO.findAll();
		this.professoresSource = professorDAO.findAll();
		
		this.professoresTarget = new ArrayList<Professor>();
		this.professores = new DualListModel<Professor>(professoresSource, professoresTarget);
		
	}
	
	public String cadastrar() {
		ConcluinteDAO concluinteDAO = new ConcluinteDAO();
		TCCDAO tccDAO = new TCCDAO();
		if (concluinteDAO.findByMatricula(this.matricula) == null) {
			Concluinte concluinte = new Concluinte();
			concluinte.setMatricula(this.matricula);
			concluinte.setNome(this.nome);
			concluinte.setEmail(this.email);
			concluinte.setTelefone(this.telefone);
			concluinte.setStatus(SituacaoConcluinte.Aberto.getValue());
			TCC tcc = new TCC();
			tcc.setAutor(concluinte);
			tcc.setOrientador(this.orientador);
			tcc.setNota(this.nota);
			tcc.setTipo(this.tipo);
			tcc.setBanca(this.professores.getTarget());
			concluinteDAO.persist(concluinte);
			tccDAO.persist(tcc);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:","Concluinte cadastrado!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:","Matrícula já cadastrada!"));
			return "cadastro_concluinte";
		}
		
		return "painel";
	}
	
	public String excluir(Concluinte c) {
		ConcluinteDAO concluinteDAO = new ConcluinteDAO();
		concluinteDAO.remove(c);
		concluinteDAO.commit();
		
		return null;
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

	public List<TipoTCC> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoTCC> tipos) {
		this.tipos = tipos;
	}

	public List<Professor> getProfessoresSource() {
		return professoresSource;
	}

	public void setProfessoresSource(List<Professor> professoresSource) {
		this.professoresSource = professoresSource;
	}

	public DualListModel<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(DualListModel<Professor> professores) {
		this.professores = professores;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public List<Professor> getProfessoresTarget() {
		return professoresTarget;
	}

	public void setProfessoresTarget(List<Professor> professoresTarget) {
		this.professoresTarget = professoresTarget;
	}

	public DataModel<Concluinte> getModel() {
		return model;
	}

	public void setModel(DataModel<Concluinte> model) {
		this.model = model;
	}

	public List<Concluinte> getConcluintes() {
		return concluintes;
	}

	public void setConcluintes(List<Concluinte> concluintes) {
		this.concluintes = concluintes;
	}
	
}
