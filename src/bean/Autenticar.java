package bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import model.Usuario;
import auxiliar.TipoUsuario;
import dao.UsuarioDAO;

@ManagedBean(name="autenticar")
@SessionScoped
public class Autenticar {
	
	private String login, senha;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	@PostConstruct
	public void criarAdmin() {
		if (usuarioDAO.findAll().size() == 0) {
			Usuario admin = new Usuario();
			admin.setLogin("admin");
			admin.setNome("admin");
			admin.setSenha("adminTSI");
			admin.setTipo(TipoUsuario.CoordenacaoTSI.getValue());
			usuarioDAO.persist(admin);
		}
	}
	
	public String efetuarLogin() {
		Usuario usuario = this.usuarioDAO.findByLogin(this.login);
		if (usuario != null && usuario.getSenha().equals(this.senha)) {
			if (usuario.getTipo().equals(TipoUsuario.CoordenacaoTSI.getValue())) {
				return "painel";
			} else if (usuario.getTipo().equals(TipoUsuario.CoordenacaoEstagio)) {
				return null;
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:","Login e/ou Senha incorretos!"));
		}
		return null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
