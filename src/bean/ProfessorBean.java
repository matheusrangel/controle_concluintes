package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.Professor;
import dao.ProfessorDAO;

@ManagedBean(name="professorBean")
public class ProfessorBean {
	private ProfessorDAO professorDAO;
	private String nome;
	
	public String cadastrar() {
		professorDAO = new ProfessorDAO();
		if (professorDAO.findByNome(this.nome) == null) {
			Professor professor = new Professor();
			professor.setNome(this.nome);
			professorDAO.persist(professor);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:","Professor cadastrado!"));
		}
		
		return "painel";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
