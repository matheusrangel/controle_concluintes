package bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Usuario;
import auxiliar.TipoUsuario;
import dao.UsuarioDAO;

@ManagedBean(name="sessao")
@SessionScoped
public class Autenticar {
	
	private String nome, login, senha, senhaAntiga, senhaNova;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void criarAdmin() {
		if (usuarioDAO.findAll().size() == 0) {
			Usuario admin = new Usuario();
			admin.setLogin("admin");
			admin.setNome("Valéria");
			admin.setSenha("admin");
			admin.setTipo(TipoUsuario.CoordenacaoTSI.getValue());
			usuarioDAO.persist(admin);
		}
	}
	
	public String efetuarLogin() {
		Usuario usuario = this.usuarioDAO.findByLogin(this.login);
		if (usuario != null && usuario.getSenha().equals(this.senha)) {
			this.usuarioLogado = usuario;
			if (usuario.getTipo().equals(0) || usuario.getTipo().equals(1)) {
				return "painel?faces-redirect=true";
			} else {
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
		
	public String efetuarCadastro() throws IOException{
		Usuario usuario = this.usuarioDAO.findByLogin(this.login);
		
		if(usuario == null){
			usuario = new Usuario();
			usuario.setTipo(1);
			usuario.setNome(nome);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuarioDAO.persist(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:","Professor de Estágio cadastrado!"));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:","Login existente!"));
		}
		
		return "painel";
	}
	
	public String alterarSenha() {
		if (this.senhaAntiga.equals(this.usuarioLogado.getSenha())) {
			this.usuarioLogado.setSenha(this.senhaNova);
			usuarioDAO.update(usuarioLogado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso: Senha alterada!",""));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: Senha antiga não informada!",""));
		}
		
		return "painel";
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

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}
	
	
	
}
