package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.TipoTCC;
import dao.TipoTCCDAO;

@ManagedBean(name="tipoTccBean")
public class TipoTCCBean {
	
	private String nome;
	private TipoTCCDAO tipoTccDAO;
	
	public String cadastrar() {
		tipoTccDAO = new TipoTCCDAO();
		if (tipoTccDAO.findByNome(this.nome) == null) {
			TipoTCC tipoTCC = new TipoTCC();
			tipoTCC.setTipo(this.nome);
			tipoTccDAO.persist(tipoTCC);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:","Tipo de TCC cadastrado!"));
		}
		
		return "painel?faces-redirect=true";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
