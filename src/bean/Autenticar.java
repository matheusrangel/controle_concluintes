package bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import model.Usuario;
import auxiliar.TipoUsuario;
import dao.UsuarioDAO;

@ManagedBean(name="sessao")
@SessionScoped
public class Autenticar {
	
	private String nome, login, senha;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuarioLogado;
	
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
			this.usuarioLogado = usuario;
			if (usuario.getTipo().equals(TipoUsuario.CoordenacaoTSI.getValue())) {
				return "painel?faces-redirect=true";
			} else if (usuario.getTipo().equals(TipoUsuario.ProfessorEstagio)) {
				return null;
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:","Login e/ou Senha incorretos!"));
		}
		return null;
	}
	
	public String logout() {
		this.usuarioLogado = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index?faces-redirect=true";
	}
	
	public void verificaPermissao(Integer permissao) throws IOException {
		if (getUsuarioLogado() != null) {
			if (!getUsuarioLogado().getTipo().equals(permissao)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:","Voce não tem permissao para acessar esta página!"));
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:","Efetue login!"));
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
		}
	}
	
	public String efetuarCadastro() {
		Usuario usuario = this.usuarioDAO.findByLogin(this.login);
		
		if(usuario == null){
			usuario = new Usuario();
			usuario.setTipo(1);
			usuario.setNome(nome);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuarioDAO.persist(usuario);
			
			return "painel?faces-redirect=true";
		}
		
		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
	
}
