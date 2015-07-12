package bean;

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
	
	public String efetuarLogin() {
		Usuario usuario = this.usuarioDAO.findByLogin(this.login);
		if (usuario != null && usuario.getSenha().equals(this.senha)) {
			if (usuario.getTipo().equals(TipoUsuario.CoordenacaoTSI)) {
				return null;
			} else if (usuario.getTipo().equals(TipoUsuario.CoordenacaoEstagio)) {
				return null;
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login e/ou Senha incorretos!"));
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
